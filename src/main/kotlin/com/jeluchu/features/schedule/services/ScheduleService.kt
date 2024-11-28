package com.jeluchu.features.schedule.services

import com.jeluchu.core.connection.RestClient
import com.jeluchu.core.extensions.needsUpdate
import com.jeluchu.core.extensions.update
import com.jeluchu.core.messages.ErrorMessages
import com.jeluchu.core.models.ErrorResponse
import com.jeluchu.core.models.jikan.anime.AnimeData.Companion.toDayEntity
import com.jeluchu.core.utils.*
import com.jeluchu.features.anime.mappers.documentToScheduleDayEntity
import com.jeluchu.features.schedule.models.ScheduleData
import com.jeluchu.features.schedule.models.ScheduleEntity
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

class ScheduleService(
    database: MongoDatabase
) {
    private val timers = database.getCollection("timers")
    private val schedules = database.getCollection("schedule")

    suspend fun getSchedule(call: RoutingCall) {
        val needsUpdate = timers.needsUpdate(
            amount = 7,
            unit = TimeUnit.DAY,
            key = TimerKey.SCHEDULE
        )

        if (needsUpdate) {
            schedules.deleteMany(Document())

            val response = ScheduleData(
                sunday = getSchedule(Day.SUNDAY).data?.map { it.toDayEntity(Day.SUNDAY) }.orEmpty(),
                friday = getSchedule(Day.FRIDAY).data?.map { it.toDayEntity(Day.FRIDAY) }.orEmpty(),
                monday = getSchedule(Day.MONDAY).data?.map { it.toDayEntity(Day.MONDAY) }.orEmpty(),
                tuesday = getSchedule(Day.TUESDAY).data?.map { it.toDayEntity(Day.TUESDAY) }.orEmpty(),
                thursday = getSchedule(Day.THURSDAY).data?.map { it.toDayEntity(Day.THURSDAY) }.orEmpty(),
                saturday = getSchedule(Day.SATURDAY).data?.map { it.toDayEntity(Day.SATURDAY) }.orEmpty(),
                wednesday = getSchedule(Day.WEDNESDAY).data?.map { it.toDayEntity(Day.WEDNESDAY) }.orEmpty()
            )

            val documentsToInsert = parseScheduleDataToDocuments(response)
            if (documentsToInsert.isNotEmpty()) schedules.insertMany(documentsToInsert)
            timers.update(TimerKey.SCHEDULE)

            call.respond(HttpStatusCode.OK, Json.encodeToString(response))
        } else {
            val elements = schedules.find().toList()
            val directory = elements.map { documentToScheduleDayEntity(it) }
            val json = Json.encodeToString(directory)
            call.respond(HttpStatusCode.OK, json)
        }
    }

    suspend fun getScheduleByDay(call: RoutingCall) {
        val param = call.parameters["day"] ?: throw IllegalArgumentException(ErrorMessages.InvalidMalId.message)
        if (parseDay(param) == null) call.respond(HttpStatusCode.BadRequest, ErrorResponse(ErrorMessages.InvalidDay.message))

        val elements = schedules.find(Filters.eq("day", param.lowercase())).toList()
        val directory = elements.map { documentToScheduleDayEntity(it) }
        val json = Json.encodeToString(directory)
        call.respond(HttpStatusCode.OK, json)
    }

    private suspend fun getSchedule(day: Day) =
        RestClient.request(BaseUrls.JIKAN + Endpoints.SCHEDULES + "/" + day, ScheduleEntity.serializer())
}