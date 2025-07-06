package com.jeluchu.features.anime.routes

import com.jeluchu.core.extensions.getToJson
import com.jeluchu.core.utils.Routes
import com.jeluchu.features.anime.services.AnimeService
import com.jeluchu.features.anime.services.DirectoryService
import com.jeluchu.features.anime.services.SeasonService
import com.mongodb.client.MongoDatabase
import io.ktor.server.routing.*

fun Route.animeEndpoints(
    mongoDatabase: MongoDatabase,
    service: AnimeService = AnimeService(mongoDatabase),
    seasonService: SeasonService = SeasonService(mongoDatabase),
    directoryService: DirectoryService = DirectoryService(mongoDatabase),
) = route(Routes.ANIME) {
    getToJson { service.getAnimeByType(call) }
    getToJson(Routes.ID) { service.getAnimeByMalId(call) }
    getToJson(Routes.LAST_EPISODES) { service.getLastEpisodes(call) }

    route(Routes.SEASON) {
        getToJson { seasonService.getAnimeBySeason(call) }
        getToJson(Routes.YEAR_INDEX) { seasonService.getYearsAndSeasons(call) }
    }

    route(Routes.DIRECTORY) {
        getToJson { service.getDirectory(call) }
        getToJson(Routes.TYPE) { directoryService.getAnimeByType(call) }
    }
}