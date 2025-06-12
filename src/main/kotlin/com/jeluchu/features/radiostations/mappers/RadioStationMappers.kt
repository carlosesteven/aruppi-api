package com.jeluchu.features.radiostations.mappers

import com.jeluchu.core.extensions.getStringSafe
import com.jeluchu.features.radiostations.models.RadioStationEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

fun documentToRadioStation(doc: Document) = RadioStationEntity(
    name = doc.getStringSafe("name"),
    image = doc.getStringSafe("image"),
    genre = doc.getStringSafe("genre"),
    stream = doc.getStringSafe("stream"),
    website = doc.getStringSafe("website")
)

fun List<Document>.documentStationsMapper(): String {
    val directory = map { documentToRadioStation(it) }
    return Json.encodeToString(directory)
}