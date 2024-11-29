package com.jeluchu.features.rankings.services

import com.jeluchu.core.connection.RestClient
import com.jeluchu.core.enums.*
import com.jeluchu.core.extensions.needsUpdate
import com.jeluchu.core.extensions.update
import com.jeluchu.core.messages.ErrorMessages
import com.jeluchu.core.models.ErrorResponse
import com.jeluchu.core.models.jikan.anime.AnimeData.Companion.toAnimeTopEntity
import com.jeluchu.core.models.jikan.character.CharacterSearch
import com.jeluchu.core.models.jikan.manga.MangaData.Companion.toMangaTopEntity
import com.jeluchu.core.models.jikan.people.PeopleData.Companion.toPeopleTopEntity
import com.jeluchu.core.models.jikan.people.PeopleSearch
import com.jeluchu.core.models.jikan.search.AnimeSearch
import com.jeluchu.core.models.jikan.search.MangaSearch
import com.jeluchu.core.utils.BaseUrls
import com.jeluchu.core.utils.Collections
import com.jeluchu.core.utils.Endpoints
import com.jeluchu.core.utils.parseDataToDocuments
import com.jeluchu.features.anime.mappers.documentToTopEntity
import com.jeluchu.features.rankings.models.AnimeTopEntity
import com.jeluchu.features.rankings.models.CharacterTopEntity
import com.jeluchu.features.rankings.models.MangaTopEntity
import com.jeluchu.features.rankings.models.PeopleTopEntity
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import fordelete.CharacterData.Companion.toCharacterTopEntity
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RankingsService(
    database: MongoDatabase
) {
    private val timers = database.getCollection(Collections.TIMERS)
    private val animeRanking = database.getCollection(Collections.ANIME_RANKING)
    private val mangaRanking = database.getCollection(Collections.MANGA_RANKING)
    private val peopleRanking = database.getCollection(Collections.PEOPLE_RANKING)
    private val characterRanking = database.getCollection(Collections.CHARACTER_RANKING)

    suspend fun getAnimeRanking(call: RoutingCall) {
        val paramType = call.parameters["type"] ?: throw IllegalArgumentException(ErrorMessages.InvalidTopAnimeType.message)
        val paramPage = call.parameters["page"]?.toInt() ?: throw IllegalArgumentException(ErrorMessages.InvalidMalId.message)
        val paramFilter = call.parameters["filter"] ?: throw IllegalArgumentException(ErrorMessages.InvalidTopAnimeFilterType.message)
        if (parseAnimeType(paramType) == null) call.respond(HttpStatusCode.BadRequest, ErrorResponse(ErrorMessages.InvalidTopAnimeType.message))
        if (parseAnimeFilterType(paramFilter) == null) call.respond(HttpStatusCode.BadRequest, ErrorResponse(ErrorMessages.InvalidTopAnimeFilterType.message))

        val timerKey = "${Collections.ANIME_RANKING}_${paramType}_${paramFilter}_${paramPage}"

        val needsUpdate = timers.needsUpdate(
            amount = 30,
            key = timerKey,
            unit = TimeUnit.DAY
        )

        if (needsUpdate) {
            animeRanking.deleteMany(
                Filters.and(
                    Filters.eq("page", paramPage),
                    Filters.eq("type", paramType),
                    Filters.eq("subtype", paramFilter)
                )
            )

            val params = mutableListOf<String>()
            params.add("type=$paramType")
            params.add("page=$paramPage")
            params.add("filter=$paramFilter")

            val response = RestClient.request(
                BaseUrls.JIKAN + Endpoints.TOP_ANIME + "?${params.joinToString("&")}",
                AnimeSearch.serializer()
            ).data?.map { anime ->
                anime.toAnimeTopEntity(
                    top = "anime",
                    page = paramPage,
                    type = paramType,
                    subType = paramFilter
                )
            }

            val documentsToInsert = parseDataToDocuments(response, AnimeTopEntity.serializer())
            if (documentsToInsert.isNotEmpty()) animeRanking.insertMany(documentsToInsert)
            timers.update(timerKey)

            call.respond(HttpStatusCode.OK, Json.encodeToString(response))
        } else {
            val elements = animeRanking.find().toList()
            val directory = elements.map { documentToTopEntity(it) }
            val json = Json.encodeToString(directory)
            call.respond(HttpStatusCode.OK, json)
        }
    }

    suspend fun getMangaRanking(call: RoutingCall) {
        val paramType = call.parameters["type"] ?: throw IllegalArgumentException(ErrorMessages.InvalidTopMangaType.message)
        val paramPage = call.parameters["page"]?.toInt() ?: throw IllegalArgumentException(ErrorMessages.InvalidMalId.message)
        val paramFilter = call.parameters["filter"] ?: throw IllegalArgumentException(ErrorMessages.InvalidTopMangaFilterType.message)
        if (parseMangaType(paramType) == null) call.respond(HttpStatusCode.BadRequest, ErrorResponse(ErrorMessages.InvalidTopMangaType.message))
        if (parseMangaFilterType(paramFilter) == null) call.respond(HttpStatusCode.BadRequest, ErrorResponse(ErrorMessages.InvalidTopMangaFilterType.message))

        val timerKey = "${Collections.MANGA_RANKING}_${paramType}_${paramFilter}_${paramPage}"
        val needsUpdate = timers.needsUpdate(
            amount = 30,
            key = timerKey,
            unit = TimeUnit.DAY
        )

        if (needsUpdate) {
            mangaRanking.deleteMany(
                Filters.and(
                    Filters.eq("page", paramPage),
                    Filters.eq("type", paramType),
                    Filters.eq("subtype", paramFilter)
                )
            )

            val params = mutableListOf<String>()
            params.add("type=$paramType")
            params.add("page=$paramPage")
            params.add("filter=$paramFilter")

            val response = RestClient.request(
                BaseUrls.JIKAN + Endpoints.TOP_MANGA + "?${params.joinToString("&")}",
                MangaSearch.serializer()
            ).data?.map { anime ->
                anime.toMangaTopEntity(
                    top = "manga",
                    page = paramPage,
                    type = paramType,
                    subType = paramFilter
                )
            }

            val documentsToInsert = parseDataToDocuments(response, MangaTopEntity.serializer())
            if (documentsToInsert.isNotEmpty()) mangaRanking.insertMany(documentsToInsert)
            timers.update(timerKey)

            call.respond(HttpStatusCode.OK, Json.encodeToString(response))
        } else {
            val elements = mangaRanking.find().toList()
            val directory = elements.map { documentToTopEntity(it) }
            val json = Json.encodeToString(directory)
            call.respond(HttpStatusCode.OK, json)
        }
    }

    suspend fun getPeopleRanking(call: RoutingCall) {
        val paramPage = call.parameters["page"]?.toInt() ?: throw IllegalArgumentException(ErrorMessages.InvalidMalId.message)
        val timerKey = "${Collections.PEOPLE_RANKING}_${paramPage}"
        val needsUpdate = timers.needsUpdate(
            amount = 30,
            key = timerKey,
            unit = TimeUnit.DAY
        )

        if (needsUpdate) { peopleRanking.deleteMany(Filters.and(Filters.eq("page", paramPage)))
            val response = RestClient.request(
                BaseUrls.JIKAN + Endpoints.TOP_PEOPLE + "?page=$paramPage",
                PeopleSearch.serializer()
            ).data?.map { anime ->
                anime.toPeopleTopEntity(
                    top = "people",
                    page = paramPage
                )
            }

            val documentsToInsert = parseDataToDocuments(response, PeopleTopEntity.serializer())
            if (documentsToInsert.isNotEmpty()) peopleRanking.insertMany(documentsToInsert)
            timers.update(timerKey)

            call.respond(HttpStatusCode.OK, Json.encodeToString(response))
        } else {
            val elements = peopleRanking.find().toList()
            val directory = elements.map { documentToTopEntity(it) }
            val json = Json.encodeToString(directory)
            call.respond(HttpStatusCode.OK, json)
        }
    }

    suspend fun getCharacterRanking(call: RoutingCall) {
        val paramPage = call.parameters["page"]?.toInt() ?: throw IllegalArgumentException(ErrorMessages.InvalidMalId.message)
        val timerKey = "${Collections.CHARACTER_RANKING}_${paramPage}"
        val needsUpdate = timers.needsUpdate(
            amount = 30,
            key = timerKey,
            unit = TimeUnit.DAY
        )

        if (needsUpdate) { characterRanking.deleteMany(Filters.and(Filters.eq("page", paramPage)))
            val response = RestClient.request(
                BaseUrls.JIKAN + Endpoints.TOP_CHARACTER + "?page=$paramPage",
                CharacterSearch.serializer()
            ).data?.map { anime ->
                anime.toCharacterTopEntity(
                    top = "character",
                    page = paramPage
                )
            }

            val documentsToInsert = parseDataToDocuments(response, CharacterTopEntity.serializer())
            if (documentsToInsert.isNotEmpty()) characterRanking.insertMany(documentsToInsert)
            timers.update(timerKey)

            call.respond(HttpStatusCode.OK, Json.encodeToString(response))
        } else {
            val elements = characterRanking.find().toList()
            val directory = elements.map { documentToTopEntity(it) }
            val json = Json.encodeToString(directory)
            call.respond(HttpStatusCode.OK, json)
        }
    }
}