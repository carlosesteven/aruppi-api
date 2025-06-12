package com.jeluchu.core.configuration

import com.jeluchu.features.anime.routes.animeEndpoints
import com.jeluchu.features.anitakume.routes.anitakumeEndpoints
import com.jeluchu.features.gallery.routes.galleryEndpoints
import com.jeluchu.features.news.routes.newsEndpoints
import com.jeluchu.features.radiostations.routes.radioStationsEndpoints
import com.jeluchu.features.rankings.routes.rankingsEndpoints
import com.jeluchu.features.schedule.routes.scheduleEndpoints
import com.jeluchu.features.themes.routes.themesEndpoints
import com.mongodb.client.MongoDatabase
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.initRoutes(
    mongoDatabase: MongoDatabase = connectToMongoDB()
) = routing {
    route("api/v5") {
        initDocumentation()
        newsEndpoints(mongoDatabase)
        animeEndpoints(mongoDatabase)
        themesEndpoints(mongoDatabase)
        galleryEndpoints(mongoDatabase)
        rankingsEndpoints(mongoDatabase)
        scheduleEndpoints(mongoDatabase)
        anitakumeEndpoints(mongoDatabase)
        radioStationsEndpoints(mongoDatabase)
    }
}