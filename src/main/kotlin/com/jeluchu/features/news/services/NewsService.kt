package com.jeluchu.features.news.services

import com.jeluchu.core.enums.TimeUnit
import com.jeluchu.core.extensions.needsUpdate
import com.jeluchu.core.extensions.update
import com.jeluchu.core.utils.Collections
import com.jeluchu.core.utils.RssSources
import com.jeluchu.core.utils.RssUrls
import com.jeluchu.core.utils.parseDataToDocuments
import com.jeluchu.features.anime.mappers.documentToAnimeTopEntity
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

class NewsService(
    database: MongoDatabase
) {
    private val timers = database.getCollection(Collections.TIMERS)
    private val spanishNews = database.getCollection(Collections.NEWS_ES)
    private val englishNews = database.getCollection(Collections.NEWS_EN)

    suspend fun getSpanishNews(call: RoutingCall) {
        val needsUpdate = timers.needsUpdate(
            amount = 1,
            key = Collections.NEWS_ES,
            unit = TimeUnit.DAY
        )

        if (needsUpdate) {
            spanishNews.deleteMany(Document())

            val response = mutableListOf<NewEntity>().apply {
                with(RssParser()) {
                    getRssChannel(RssUrls.MANGALATAM).toNews(RssSources.MANGALATAM, this@apply)
                    getRssChannel(RssUrls.SOMOSKUDASAI).toNews(RssSources.SOMOSKUDASAI, this@apply)
                    getRssChannel(RssUrls.CRUNCHYROLL).toNews(RssSources.CRUNCHYROLL, this@apply)
                    getRssChannel(RssUrls.RAMENPARADOS).toNews(RssSources.RAMENPARADOS, this@apply)
                    getRssChannel(RssUrls.ANMOSUGOI).toNews(RssSources.ANMOSUGOI, this@apply)
                }
            }.shuffled()

            val documentsToInsert = parseDataToDocuments(response, NewEntity.serializer())
            if (documentsToInsert.isNotEmpty()) spanishNews.insertMany(documentsToInsert)
            timers.update(Collections.NEWS_ES)

            val elements = documentsToInsert.map { documentToNewsEntity(it) }
            call.respond(HttpStatusCode.OK, Json.encodeToString(elements))
        } else {
            val animes = spanishNews
                .find()
                .toList()

            val elements = animes.map { documentToAnimeTopEntity(it) }
            call.respond(HttpStatusCode.OK, Json.encodeToString(elements))
        }
    }

    suspend fun getEnglishNews(call: RoutingCall) {
        val needsUpdate = timers.needsUpdate(
            amount = 1,
            key = Collections.NEWS_EN,
            unit = TimeUnit.DAY
        )

        if (needsUpdate) {
            englishNews.deleteMany(Document())

            val response = mutableListOf<NewEntity>().apply {
                with(RssParser()) {
                    getRssChannel(RssUrls.OTAKUMODE).toNews(RssSources.OTAKUMODE, this@apply)
                    getRssChannel(RssUrls.MYANIMELIST).toNews(RssSources.MYANIMELIST, this@apply)
                    getRssChannel(RssUrls.HONEYSANIME).toNews(RssSources.HONEYSANIME, this@apply)
                    getRssChannel(RssUrls.ANIMEHUNCH).toNews(RssSources.ANIMEHUNCH, this@apply)
                }
            }.shuffled()

            val documentsToInsert = parseDataToDocuments(response, NewEntity.serializer())
            if (documentsToInsert.isNotEmpty()) englishNews.insertMany(documentsToInsert)
            timers.update(Collections.NEWS_EN)

            val elements = documentsToInsert.map { documentToNewsEntity(it) }
            call.respond(HttpStatusCode.OK, Json.encodeToString(elements))
        } else {
            val animes = englishNews
                .find()
                .toList()

            val elements = animes.map { documentToAnimeTopEntity(it) }
            call.respond(HttpStatusCode.OK, Json.encodeToString(elements))
        }
    }
}