package com.jeluchu.features.radiostations.routes

import com.jeluchu.core.extensions.getToJson
import com.jeluchu.core.utils.Routes
import com.jeluchu.features.radiostations.services.RadioStationsService
import com.mongodb.client.MongoDatabase
import io.ktor.server.routing.*

fun Route.radioStationsEndpoints(
    mongoDatabase: MongoDatabase,
    service: RadioStationsService = RadioStationsService(mongoDatabase)
) = route(Routes.RADIO_STATIONS) {
    getToJson { service.getStations(call) }
}