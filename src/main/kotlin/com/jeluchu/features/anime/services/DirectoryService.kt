package com.jeluchu.features.anime.services

import com.jeluchu.core.enums.TimeUnit
import com.jeluchu.core.enums.parseAnimeType
import com.jeluchu.core.extensions.needsUpdate
import com.jeluchu.core.extensions.update
import com.jeluchu.core.messages.ErrorMessages
import com.jeluchu.core.models.ErrorResponse
import com.jeluchu.core.utils.Collections
import com.jeluchu.core.utils.TimerKey
import com.jeluchu.features.anime.mappers.documentToAnimeTypeEntity
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

class DirectoryService(
    private val database: MongoDatabase
) {
    private val timers = database.getCollection(Collections.TIMERS)
    private val directory = database.getCollection(Collections.ANIME_DETAILS)

    suspend fun getAnimeByType(call: RoutingCall) {
        val param = call.parameters["type"] ?: throw IllegalArgumentException(ErrorMessages.InvalidAnimeType.message)
        if (parseAnimeType(param) == null) call.respond(
            HttpStatusCode.BadRequest,
            ErrorResponse(ErrorMessages.InvalidAnimeType.message)
        )

        val timerKey = "${TimerKey.ANIME_TYPE}${param.lowercase()}"
        val needsUpdate = timers.needsUpdate(
            amount = 30,
            key = timerKey,
            unit = TimeUnit.DAY,
        )

        if (needsUpdate) {
            val collection = database.getCollection(timerKey)
            collection.deleteMany(Document())

            val animes = directory.find(Filters.eq("type", param.uppercase())).toList()
            val animeTypes = animes.map { documentToAnimeTypeEntity(it) }
            val documents = animeTypes.map { anime -> Document.parse(Json.encodeToString(anime)) }
            if (documents.isNotEmpty()) collection.insertMany(documents)
            timers.update(timerKey)

            call.respond(HttpStatusCode.OK, Json.encodeToString(animeTypes))
        } else {
            val elements = directory.find().toList()
            call.respond(HttpStatusCode.OK, elements.documentAnimeTypeMapper())
        }
    }

    private fun List<Document>.documentAnimeTypeMapper(): String {
        val directory = map { documentToAnimeTypeEntity(it) }
        return Json.encodeToString(directory)
    }
}