package com.jeluchu.core.models

import java.time.Instant

data class TimestampEntry(
    val key: String,
    val lastUpdated: Instant
)