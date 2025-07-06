package com.jeluchu.features.anime.services

import com.jeluchu.core.enums.AnimeStatusTypes
import com.jeluchu.core.models.documentToSimpleAnimeEntity
import com.jeluchu.core.utils.Collections
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

class TagsService(
    private val database: MongoDatabase,
    private val directory: MongoCollection<Document> = database.getCollection(Collections.ANIME_DIRECTORY)
) {
    suspend fun getAnimeByAnyTag(call: RoutingCall) {
        val tags = call.request.queryParameters["tags"].orEmpty()

        val tagsList = if (tags.isNotEmpty()) {
            tags.split(",").map { it.trim() }
        } else emptyList()

        if (tagsList.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "No tags provided")
            return
        }

        val query = directory.find(
            Filters.and(
                Filters.or(
                    Filters.`in`("tags.es", tagsList),
                    Filters.`in`("tags.en", tagsList)
                ),
                Filters.`in`("status", listOf(AnimeStatusTypes.FINISHED, AnimeStatusTypes.ONGOING)),
                Filters.ne("type", "MUSIC"),
                Filters.ne("type", "PV")
            )
        )
            .toList()
            .map { documentToSimpleAnimeEntity(it) }

        call.respond(HttpStatusCode.OK, Json.encodeToString(query))
    }
}