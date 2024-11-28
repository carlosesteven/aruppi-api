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
    route(Routes.TOP) {
        route(Routes.ANIME) {
            getToJson(Routes.TOP_ANIME) { service.getAnimeRanking(call) }
        }
    }

    getToJson(Routes.TOP_MANGA) {  }
}