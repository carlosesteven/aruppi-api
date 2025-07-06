package com.jeluchu.core.models

import com.jeluchu.core.extensions.getDocumentSafe
import com.jeluchu.core.extensions.getIntSafe
import com.jeluchu.core.extensions.getStringSafe
import com.jeluchu.features.anime.mappers.documentToMultipleLanguageLists
import com.jeluchu.features.anime.models.anime.MultipleLanguageLists
import com.jeluchu.features.anime.models.tags.TagsAnimeEntity
import kotlinx.serialization.Serializable
import org.bson.Document

@Serializable
data class SimpleAnimeEntity(
    val malId: Int,
    val type: String,
    val title: String,
    val image: String,
    val score: String
)

fun documentToSimpleAnimeEntity(doc: Document) = SimpleAnimeEntity(
    malId = doc.getIntSafe("malId"),
    title = doc.getStringSafe("title"),
    type = doc.getStringSafe("type"),
    score = doc.getStringSafe("score"),
    image = doc.getStringSafe("poster")
)