package com.jeluchu.features.anime.mappers

import com.jeluchu.core.extensions.getIntSafe
import com.jeluchu.core.extensions.getStringSafe
import com.jeluchu.features.anime.models.seasons.SeasonAnimeEntity
import org.bson.Document

fun documentToSeasonEntity(doc: Document) = SeasonAnimeEntity(
    score = doc.getStringSafe("score"),
    malId = doc.getIntSafe("malId"),
    image = doc.getStringSafe("poster"),
    title = doc.getStringSafe("title")
)