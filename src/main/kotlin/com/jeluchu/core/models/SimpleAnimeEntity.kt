package com.jeluchu.core.models

import com.jeluchu.core.extensions.getDocumentSafe
import com.jeluchu.core.extensions.getIntSafe
import com.jeluchu.core.extensions.getStringSafe
import com.jeluchu.core.utils.SeasonCalendar
import kotlinx.serialization.Serializable
import org.bson.Document

@Serializable
data class SimpleAnimeEntity(
    val malId: Int,
    val type: String,
    val title: String,
    val image: String,
    val score: String,
    val season: SeasonInfo
) {
    @Serializable
    data class SeasonInfo(
        val year: Int? = null,
        val station: String? = null
    )
}

fun documentToSimpleAnimeEntity(doc: Document) = SimpleAnimeEntity(
    malId = doc.getIntSafe("malId"),
    title = doc.getStringSafe("title"),
    type = doc.getStringSafe("type"),
    score = doc.getStringSafe("score"),
    image = doc.getStringSafe("poster"),
    season = doc.getDocumentSafe("season")?.let { documentToSeasonInfo(it) } ?: SimpleAnimeEntity.SeasonInfo(),
)

fun documentToSeasonInfo(doc: Document) = SimpleAnimeEntity.SeasonInfo(
    year = doc.getIntSafe("year"),
    station = doc.getStringSafe("station")
)