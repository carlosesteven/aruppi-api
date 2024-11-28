package com.jeluchu.features.rankings.routes

import com.jeluchu.core.extensions.getToJson
import com.jeluchu.core.utils.Routes
import com.jeluchu.features.rankings.services.RankingsService
import com.mongodb.client.MongoDatabase
import io.ktor.server.routing.*

fun Route.rankingsEndpoints(
    mongoDatabase: MongoDatabase,
    service: RankingsService = RankingsService(mongoDatabase)
) {
    getToJson(Routes.TOP_ANIME) { service.getAnimeByMalId(call) }
    getToJson(Routes.TOP_MANGA) { service.getDirectory(call) }
}