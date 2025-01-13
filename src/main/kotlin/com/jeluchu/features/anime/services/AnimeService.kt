package com.jeluchu.features.anime.services

import com.jeluchu.core.connection.RestClient
import com.jeluchu.core.enums.AnimeTypes
import com.jeluchu.core.enums.Day
import com.jeluchu.core.enums.TimeUnit
import com.jeluchu.core.enums.parseAnimeType
import com.jeluchu.core.extensions.needsUpdate
import com.jeluchu.core.extensions.update
import com.jeluchu.core.messages.ErrorMessages
import com.jeluchu.core.models.ErrorResponse
import com.jeluchu.core.models.PaginationResponse
import com.jeluchu.core.models.animeflv.lastepisodes.LastEpisodeData.Companion.toEpisodeEntity
import com.jeluchu.core.models.animeflv.lastepisodes.LastEpisodes
import com.jeluchu.core.models.jikan.anime.AnimeData.Companion.toDayEntity
import com.jeluchu.core.utils.BaseUrls
import com.jeluchu.core.utils.Collections
import com.jeluchu.core.utils.Endpoints
import com.jeluchu.core.utils.TimerKey
import com.jeluchu.features.anime.mappers.*
import com.jeluchu.features.schedule.models.ScheduleEntity
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

class AnimeService(
    database: MongoDatabase
) {
    private val timers = database.getCollection(Collections.TIMERS)
    private val directoryCollection = database.getCollection(Collections.ANIME_DETAILS)
    private val lastEpisodesCollection = database.getCollection(Collections.LAST_EPISODES)

    suspend fun getDirectory(call: RoutingCall) = try {
        val type = call.request.queryParameters["type"].orEmpty()
        val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
        val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10

        if (page < 1 || size < 1) call.respond(HttpStatusCode.BadRequest, ErrorMessages.InvalidSizeAndPage.message)
        val skipCount = (page - 1) * size

        if (parseAnimeType(type) == null) {
            val animes = directoryCollection
                .find()
                .skip(skipCount)
                .limit(size)
                .toList()

            val elements = animes.map { documentToAnimeTypeEntity(it) }

            val response = PaginationResponse(
                page = page,
                size = size,
                data = elements
            )

            call.respond(HttpStatusCode.OK, Json.encodeToString(response))
        } else {
            val animes = directoryCollection
                .find(Filters.eq("type", type.uppercase()))
                .skip(skipCount)
                .limit(size)
                .toList()

            val elements = animes.map { documentToAnimeTypeEntity(it) }

            val response = PaginationResponse(
                page = page,
                size = size,
                data = elements
            )

            call.respond(HttpStatusCode.OK, Json.encodeToString(response))
        }
    } catch (ex: Exception) {
        call.respond(HttpStatusCode.Unauthorized, ErrorResponse(ErrorMessages.UnauthorizedMongo.message))
    }

    suspend fun getAnimeByMalId(call: RoutingCall) = try {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException(ErrorMessages.InvalidMalId.message)
        directoryCollection.find(Filters.eq("malId", id)).firstOrNull()?.let { anime ->
            val info = documentToMoreInfoEntity(anime)
            call.respond(HttpStatusCode.OK, Json.encodeToString(info))
        } ?: call.respond(HttpStatusCode.NotFound, ErrorResponse(ErrorMessages.AnimeNotFound.message))
    } catch (ex: Exception) {
        call.respond(HttpStatusCode.NotFound, ErrorResponse(ErrorMessages.InvalidInput.message))
    }

    suspend fun getLastEpisodes(call: RoutingCall) = try {
        val needsUpdate = timers.needsUpdate(
            amount = 3,
            unit = TimeUnit.HOUR,
            key = TimerKey.LAST_EPISODES
        )

        if (needsUpdate) {
            lastEpisodesCollection.deleteMany(Document())

            val episodes = getLastedEpisodes().data?.map { it.toEpisodeEntity() }.orEmpty()
            val documents = episodes.map { anime -> Document.parse(Json.encodeToString(anime)) }
            if (documents.isNotEmpty()) lastEpisodesCollection.insertMany(documents)
            timers.update(TimerKey.LAST_EPISODES)

            call.respond(HttpStatusCode.OK, Json.encodeToString(episodes))
        } else {
            val elements = lastEpisodesCollection.find().toList()
            call.respond(HttpStatusCode.OK, elements.documentToLastEpisodesEntity())
        }
    } catch (ex: Exception) {
        call.respond(HttpStatusCode.Unauthorized, ErrorResponse(ErrorMessages.UnauthorizedMongo.message))
    }

    private suspend fun getLastedEpisodes() = RestClient.requestWithDelay(
        url = BaseUrls.ANIME_FLV + Endpoints.LAST_EPISODES,
        deserializer = LastEpisodes.serializer()
    )

    private fun List<Document>.documentToLastEpisodesEntity(): String {
        val directory = map { documentToLastEpisodesEntity(it) }
        return Json.encodeToString(directory)
    }
}