package com.jeluchu.features.anime.services

import com.jeluchu.core.enums.parseSeasons
import com.jeluchu.core.models.documentToSimpleAnimeEntity
import com.jeluchu.core.utils.Collections
import com.jeluchu.core.utils.SeasonCalendar
import com.jeluchu.features.anime.models.seasons.YearSeasons
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Accumulators
import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document
import java.time.Year

class SeasonService(
    private val database: MongoDatabase,
    private val directory: MongoCollection<Document> = database.getCollection(Collections.ANIME_DIRECTORY)
) {
    suspend fun getAnimeBySeason(call: RoutingCall) {
        val year = call.request.queryParameters["year"]?.toInt() ?: SeasonCalendar.currentYear
        val station = parseSeasons(call.request.queryParameters["station"] ?: SeasonCalendar.currentSeason.name)
            ?: SeasonCalendar.currentSeason

        val query = directory.find(
            Filters.and(
                Filters.eq("season.year", year),
                Filters.eq("season.station", station),
                Filters.ne("type", "MUSIC"),
                Filters.ne("type", "PV"),
            )
        )
            .toList()
            .map { documentToSimpleAnimeEntity(it) }

        call.respond(HttpStatusCode.OK, Json.encodeToString(query))
    }

    suspend fun getYearsAndSeasons(call: RoutingCall) {
        val currentYear = Year.now().value
        val validSeasons = listOf("SUMMER", "FALL", "WINTER", "SPRING")

        val pipeline = listOf(
            Aggregates.match(
                Document(
                    "\$and", listOf(
                        Document("season.year", Document("\$gt", 0)),
                        Document("season.year", Document("\$lte", currentYear)),
                        Document("season.station", Document("\$in", validSeasons))
                    )
                )
            ),
            Aggregates.group(
                "\$season.year",
                Accumulators.addToSet("seasons", "\$season.station")
            ),
            Aggregates.project(
                Document().apply {
                    put("year", "\$_id")
                    put("seasons", 1)
                    put("_id", 0)
                }
            ),
            Aggregates.sort(Sorts.descending("year"))
        )

        val results = directory.aggregate(pipeline).toList()
        val index = results.map { document ->
            YearSeasons(
                year = document.getInteger("year"),
                seasons = document.getList("seasons", String::class.java)
            )
        }

        call.respond(HttpStatusCode.OK, Json.encodeToString(index))
    }
}