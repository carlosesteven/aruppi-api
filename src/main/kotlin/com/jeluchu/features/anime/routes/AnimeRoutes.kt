package com.jeluchu.features.anime.routes

import com.jeluchu.core.extensions.getToJson
import com.jeluchu.core.utils.Routes
import com.jeluchu.features.anime.services.AnimeService
import com.mongodb.client.MongoDatabase
import io.ktor.server.routing.*

fun Route.animeEndpoints(
    mongoDatabase: MongoDatabase,
    service: AnimeService = AnimeService(mongoDatabase)
) {
    getToJson(Routes.DIRECTORY) { service.getDirectory(call) }
    getToJson(Routes.ANIME_DETAILS) { service.getAnimeByMalId(call) }
}