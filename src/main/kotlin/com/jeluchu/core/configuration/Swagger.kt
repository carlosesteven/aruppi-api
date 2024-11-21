package com.jeluchu.core.configuration

import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Route.initDocumentation() {
    swaggerUI(
        path = "/",
        swaggerFile = "openapi/documentation.yaml"
    )
}