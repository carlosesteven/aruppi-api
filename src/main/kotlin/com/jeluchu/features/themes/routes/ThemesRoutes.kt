package com.jeluchu.features.themes.routes

import com.jeluchu.core.extensions.getToJson
import com.jeluchu.core.utils.Routes
import com.jeluchu.features.schedule.services.ScheduleService
import com.jeluchu.features.themes.services.AnimeThemesService
import com.mongodb.client.MongoDatabase
import io.ktor.server.routing.*

fun Route.themesEndpoints(
    mongoDatabase: MongoDatabase,
    service: AnimeThemesService = AnimeThemesService(mongoDatabase)
) = route(Routes.THEMES) {
    getToJson(Routes.ANIME) { service.getAnimeThemes(call) }
}