package com.jeluchu.features.radiostations.services

import com.jeluchu.core.utils.Collections
import com.jeluchu.features.radiostations.mappers.documentStationsMapper
import com.mongodb.client.MongoDatabase
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class RadioStationsService(
    database: MongoDatabase
) {
    private val radioStations = database.getCollection(Collections.RADIO)

    suspend fun getStations(call: RoutingCall) {
        val elements = radioStations.find().toList()
        call.respond(HttpStatusCode.OK, elements.documentStationsMapper())
    }
}