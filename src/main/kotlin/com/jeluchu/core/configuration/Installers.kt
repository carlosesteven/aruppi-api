package com.jeluchu.core.configuration

import com.jeluchu.core.messages.ErrorMessages
import com.jeluchu.core.models.ErrorResponse
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.initInstallers() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, _ ->
            call.respond(HttpStatusCode.NotFound,
                ErrorResponse(ErrorMessages.NotFound.message)
            )
        }
    }

    install(ContentNegotiation) {
        json()
    }
}