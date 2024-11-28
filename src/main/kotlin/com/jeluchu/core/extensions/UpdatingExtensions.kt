package com.jeluchu.core.extensions

import com.jeluchu.core.utils.TimeUnit
import com.jeluchu.core.utils.TimerKey
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.ReplaceOptions
import org.bson.Document
import java.time.Duration
import java.time.Instant
import java.util.*

fun MongoCollection<Document>.needsUpdate(
    key: String,
    amount: Long = 5,
    unit: TimeUnit = TimeUnit.HOUR
): Boolean {
    val currentTime = Instant.now()
    val timestampEntry = find(eq(TimerKey.KEY, key)).firstOrNull()

    return if (timestampEntry == null) true else {
        val lastUpdatedDate = timestampEntry.getDate(TimerKey.LAST_UPDATED)
        val lastUpdated = lastUpdatedDate.toInstant()
        val duration = Duration.between(lastUpdated, currentTime)

        when (unit) {
            TimeUnit.DAY -> duration.toDays() >= amount
            TimeUnit.HOUR -> duration.toHours() >= amount
            TimeUnit.MINUTE -> duration.toMinutes() >= amount
            TimeUnit.SECOND -> duration.toSeconds() >= amount
        }
    }
}

fun MongoCollection<Document>.update(key: String) {
    val currentTime = Instant.now()
    val newTimestampDocument = Document(TimerKey.KEY, key)
        .append(TimerKey.LAST_UPDATED, Date.from(currentTime))

    replaceOne(
        eq(TimerKey.KEY, TimerKey.SCHEDULE),
        newTimestampDocument,
        ReplaceOptions().upsert(true)
    )
}