package com.jeluchu.core.connection

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json

object RestClient {
    private val client = HttpClient(CIO)
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun <T> request(
        url: String,
        deserializer: DeserializationStrategy<T>
    ): T {
        return runCatching {
            val response = client.get(url) {
                headers { append(HttpHeaders.Accept, ContentType.Application.Json.toString()) }
            }

            json.decodeFromString(deserializer, response.bodyAsText())
        }.getOrElse { throwable -> throw throwable }
    }

    suspend fun <T> requestWithDelay(
        url: String,
        delay: Long = 1000,
        deserializer: DeserializationStrategy<T>
    ): T {
        return runCatching {
            val response = client.get(url) {
                headers { append(HttpHeaders.Accept, ContentType.Application.Json.toString()) }
            }

            delay(delay)
            json.decodeFromString(deserializer, response.bodyAsText())
        }.getOrElse { throwable -> throw throwable }
    }
}