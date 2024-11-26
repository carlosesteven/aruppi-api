package com.jeluchu.core.extensions

import io.ktor.http.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun Route.getToJson(
    path: String,
    request: suspend RoutingContext.() -> Unit
): Route = get(path) {
    call.response.headers.append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
    withContext(Dispatchers.IO) { request() }
}