package com.jeluchu.features.anime.services

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
        val tagsParam = call.request.queryParameters["tags"].orEmpty()

        val tags = if (tagsParam.isNotEmpty()) {
            tagsParam.split(",").map { it.trim() }
        } else emptyList()

        if (tags.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "No tags provided")
            return
        }

        val query = directory.find(
            Filters.or(
                Filters.`in`("tags.es", tags),
                Filters.`in`("tags.en", tags),
                Filters.ne("type", "MUSIC"),
                Filters.ne("type", "PV"),
            )
        )
            .toList()
            .map { documentToSimpleAnimeEntity(it) }

        call.respond(HttpStatusCode.OK, Json.encodeToString(query))
    }
}