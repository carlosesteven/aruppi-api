package com.jeluchu.features.schedule.routes

import com.jeluchu.core.extensions.getToJson
import com.jeluchu.core.utils.Routes
import com.jeluchu.features.schedule.services.ScheduleService
import com.mongodb.client.MongoDatabase
import io.ktor.server.routing.*

fun Route.scheduleEndpoints(
    mongoDatabase: MongoDatabase,
    service: ScheduleService = ScheduleService(mongoDatabase)
) {
    getToJson(Routes.SCHEDULE) { service.getSchedule(call) }
    getToJson(Routes.SCHEDULE_DAY) { service.getScheduleByDay(call) }
}