package com.jeluchu.features.anitakume.routes

import com.jeluchu.core.extensions.getToJson
import com.jeluchu.core.utils.Routes
import com.jeluchu.features.anitakume.services.AnitakumeService
import com.jeluchu.features.news.services.NewsService
import com.mongodb.client.MongoDatabase
import io.ktor.server.routing.*

fun Route.anitakumeEndpoints(
    mongoDatabase: MongoDatabase,
    service: AnitakumeService = AnitakumeService(mongoDatabase)
) = route(Routes.ANITAKUME) {
    getToJson { service.getEpisodes(call) }
}