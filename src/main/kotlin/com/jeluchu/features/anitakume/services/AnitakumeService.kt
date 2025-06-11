package com.jeluchu.features.anitakume.services

import com.jeluchu.core.enums.TimeUnit
import com.jeluchu.core.extensions.needsUpdate
import com.jeluchu.core.extensions.update
import com.jeluchu.core.utils.Collections
import com.jeluchu.core.utils.RssSources
import com.jeluchu.core.utils.RssUrls
import com.jeluchu.core.utils.parseDataToDocuments
import com.jeluchu.features.anitakume.mappers.documentToAnitakumeEntity
import com.jeluchu.features.anitakume.mappers.toPodcast
import com.jeluchu.features.anitakume.models.AnitakumeEntity
import com.jeluchu.features.news.mappers.documentToNewsEntity
import com.jeluchu.features.news.mappers.toNews
import com.jeluchu.features.news.models.NewEntity
import com.mongodb.client.MongoDatabase
import com.prof18.rssparser.RssParser
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

class AnitakumeService(
    database: MongoDatabase
) {
    private val timers = database.getCollection(Collections.TIMERS)
    private val anitakume = database.getCollection(Collections.ANITAKUME)

    suspend fun getEpisodes(call: RoutingCall) {
        val needsUpdate = timers.needsUpdate(
            amount = 1,
            key = Collections.ANITAKUME,
            unit = TimeUnit.DAY
        )

        if (needsUpdate) {
            anitakume.deleteMany(Document())

            val response = mutableListOf<AnitakumeEntity>().apply {
                RssParser().getRssChannel(RssUrls.ANITAKUME).toPodcast(this@apply)
            }

            val documentsToInsert = parseDataToDocuments(response, AnitakumeEntity.serializer())
            if (documentsToInsert.isNotEmpty()) anitakume.insertMany(documentsToInsert)
            timers.update(Collections.ANITAKUME)

            val elements = documentsToInsert.map { documentToAnitakumeEntity(it) }
            call.respond(HttpStatusCode.OK, Json.encodeToString(elements))
        } else {
            val animes = anitakume
                .find()
                .toList()

            val elements = animes.map { documentToAnitakumeEntity(it) }
            call.respond(HttpStatusCode.OK, Json.encodeToString(elements))
        }
    }
}