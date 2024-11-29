package com.jeluchu.core.utils

import com.jeluchu.features.rankings.models.AnimeTopEntity
import com.jeluchu.features.schedule.models.DayEntity
import com.jeluchu.features.schedule.models.ScheduleData
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

fun parseTopDataToDocuments(data: ScheduleData): List<Document> {
    val documents = mutableListOf<Document>()
    fun processDay(dayList: List<DayEntity>?) {
        dayList?.forEach { animeData ->
            val animeJsonString = Json.encodeToString(animeData)
            val document = Document.parse(animeJsonString)
            documents.add(document)
        }
    }

    processDay(data.monday)
    processDay(data.tuesday)
    processDay(data.wednesday)
    processDay(data.thursday)
    processDay(data.friday)
    processDay(data.saturday)
    processDay(data.sunday)

    return documents
}

fun parseTopDataToDocuments(data: List<AnimeTopEntity>?): List<Document> {
    val documents = mutableListOf<Document>()
    data?.forEach { animeData ->
        val animeJsonString = Json.encodeToString(animeData)
        val document = Document.parse(animeJsonString)
        documents.add(document)
    }
    return documents
}

fun <T> parseDataToDocuments(data: List<T>?, serializer: KSerializer<T>): List<Document> {
    val documents = mutableListOf<Document>()
    data?.forEach { item ->
        val jsonString = Json.encodeToString(serializer, item)
        val document = Document.parse(jsonString)
        documents.add(document)
    }
    return documents
}