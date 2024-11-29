package com.jeluchu.features.schedule.routes

import com.jeluchu.core.extensions.getToJson
import com.jeluchu.core.utils.Routes
import com.jeluchu.features.schedule.services.ScheduleService
import com.mongodb.client.MongoDatabase
import io.ktor.server.routing.*

fun Route.scheduleEndpoints(
    mongoDatabase: MongoDatabase,
    service: ScheduleService = ScheduleService(mongoDatabase)
) = route(Routes.SCHEDULE) {
    getToJson { service.getSchedule(call) }
    getToJson(Routes.DAY) { service.getScheduleByDay(call) }
}