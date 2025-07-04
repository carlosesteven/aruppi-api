package com.jeluchu.core.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import org.bson.Document

fun <T> parseDataToDocuments(data: List<T>?, serializer: KSerializer<T>): List<Document> {
    val documents = mutableListOf<Document>()
    data?.forEach { item ->
        val jsonString = Json.encodeToString(serializer, item)
        val document = Document.parse(jsonString)
        documents.add(document)
    }
    return documents
}