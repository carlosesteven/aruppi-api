package com.jeluchu.features.themes.services

import com.jeluchu.core.extensions.badRequestError
import com.jeluchu.core.extensions.getIntSafeQueryParam
import com.jeluchu.core.messages.ErrorMessages
import com.jeluchu.core.models.PaginationResponse
import com.jeluchu.core.utils.Collections
import com.jeluchu.features.anime.mappers.documentToAnimesThemeEntity
import com.mongodb.client.MongoDatabase
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AnimeThemesService(
    private val database: MongoDatabase
) {
    private val themesDirectory = database.getCollection(Collections.ANIME_THEMES)
    private val artistsDirectory = database.getCollection(Collections.ARTISTS_INDEX)

    suspend fun getAnimeThemes(call: RoutingCall) {
        val page = call.getIntSafeQueryParam("page", 1)
        val size = call.getIntSafeQueryParam("size", 25)

        val skipCount = (page - 1) * size
        if (page < 1 || size < 1) call.badRequestError(ErrorMessages.InvalidSizeAndPage.message)

        val query = themesDirectory
            .find()
            .skip(skipCount)
            .limit(size)
            .toList()
            .map { documentToAnimesThemeEntity(it) }

        val paginate = PaginationResponse(
            page = page,
            data = query,
            size = query.size
        )

        call.respond(HttpStatusCode.OK, Json.encodeToString(paginate))
    }
}