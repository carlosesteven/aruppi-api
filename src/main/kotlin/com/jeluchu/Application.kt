@file:Suppress("unused")

package com.jeluchu

import com.jeluchu.core.configuration.initInstallers
import com.jeluchu.core.configuration.initRoutes
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    initInstallers()
    initRoutes()
}