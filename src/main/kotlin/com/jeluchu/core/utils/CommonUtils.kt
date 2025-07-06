package com.jeluchu.core.utils

import com.jeluchu.core.models.PaginationResponse
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.bson.conversions.Bson

fun <T> getRemoteData(
    filters: Bson,
    mapper: (Document) -> T,
    onQuerySuccess: (List<T>) -> Unit,
    newCollection: MongoCollection<Document>,
    remoteCollection: MongoCollection<Document>,
) {
    newCollection.deleteMany(Document())

    val query = remoteCollection
        .find(filters)
        .toList()
        .map { mapper(it) }

    onQuerySuccess(query)
}

suspend fun <T> getLocalData(
    page: Int,
    size: Int,
    skipCount: Int,
    mapper: (Document) -> T,
    collection: MongoCollection<Document>,
    onQuerySuccess: suspend (PaginationResponse<T>) -> Unit
) {
    val query = collection
        .find()
        .skip(skipCount)
        .limit(size)
        .toList()
        .map { mapper(it) }

    val paginate = PaginationResponse(
        page = page,
        data = query,
        size = query.size
    )

    onQuerySuccess(paginate)
}