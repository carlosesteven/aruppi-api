package com.jeluchu.features.anime.routes

import com.jeluchu.features.anime.services.AnimeService
import com.mongodb.client.MongoDatabase
import io.ktor.server.routing.*

fun Route.animeEndpoints(
    mongoDatabase: MongoDatabase,
    service: AnimeService = AnimeService(mongoDatabase)
) {
    get("/directory") { service.getDirectory(call) }
    get("/anime/{id}") { service.getAnimeByMalId(call) }
}