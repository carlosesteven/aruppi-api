package com.jeluchu.features.rankings.services

import com.jeluchu.core.connection.RestClient
import com.jeluchu.core.enums.*
import com.jeluchu.core.extensions.needsUpdate
import com.jeluchu.core.extensions.update
import com.jeluchu.core.messages.ErrorMessages
import com.jeluchu.core.models.ErrorResponse
import com.jeluchu.core.models.jikan.anime.AnimeData.Companion.toTopEntity
import com.jeluchu.core.models.jikan.search.Search
import com.jeluchu.core.utils.*
import com.jeluchu.features.anime.mappers.documentToScheduleDayEntity
import com.jeluchu.features.anime.mappers.documentToTopEntity
import com.jeluchu.features.schedule.models.ScheduleEntity
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

class RankingsService(
    database: MongoDatabase
) {
    private val timers = database.getCollection("timers")
    private val ranking = database.getCollection("ranking")

    suspend fun getAnimeRanking(call: RoutingCall) {
        val paramType = call.parameters["type"] ?: throw IllegalArgumentException(ErrorMessages.InvalidMalId.message)
        val paramFilter = call.parameters["filter"] ?: throw IllegalArgumentException(ErrorMessages.InvalidMalId.message)
        val paramPage = call.parameters["page"]?.toInt() ?: throw IllegalArgumentException(ErrorMessages.InvalidMalId.message)
        if (parseType(paramType) == null) call.respond(HttpStatusCode.BadRequest, ErrorResponse(ErrorMessages.InvalidDay.message))
        if (parseFilterType(paramFilter) == null) call.respond(HttpStatusCode.BadRequest, ErrorResponse(ErrorMessages.InvalidDay.message))

        val timerKey = "${TimerKey.RANKING}_${paramType}_${paramFilter}_${paramPage}"

        val needsUpdate = timers.needsUpdate(
            amount = 1,
            key = timerKey,
            unit = TimeUnit.MINUTE
        )

        if (needsUpdate) {
            ranking.deleteMany(Document())

            val params = mutableListOf<String>()
            params.add("type=$paramType")
            params.add("page=$paramPage")
            params.add("filter=$paramFilter")

            val response = RestClient.request(
                BaseUrls.JIKAN + Endpoints.TOP_ANIME + "?${params.joinToString("&")}",
                Search.serializer()
            ).data?.mapIndexed { index, anime ->
                anime.toTopEntity(
                    page = paramPage,
                    rank = index + 1,
                    type = paramType,
                    subType = paramFilter
                )
            }

            val documentsToInsert = parseScheduleDataToDocuments(response)
            if (documentsToInsert.isNotEmpty()) ranking.insertMany(documentsToInsert)
            timers.update(timerKey)

            call.respond(HttpStatusCode.OK, Json.encodeToString(response))
        } else {
            val elements = ranking.find().toList()
            val directory = elements.map { documentToTopEntity(it) }
            val json = Json.encodeToString(directory)
            call.respond(HttpStatusCode.OK, json)
        }
    }
}

