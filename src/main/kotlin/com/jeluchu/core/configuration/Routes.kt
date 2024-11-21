package com.jeluchu.core.configuration

import com.jeluchu.features.anime.routes.animeEndpoints
import com.mongodb.client.MongoDatabase
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.initRoutes(
    mongoDatabase: MongoDatabase = connectToMongoDB()
) = routing {
    route("api/v5") {
        initDocumentation()
        animeEndpoints(mongoDatabase)
    }
}