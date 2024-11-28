package com.jeluchu.features.schedule.models

import kotlinx.serialization.Serializable

@Serializable
data class DayEntity(
    val malId: Int,
    val day: String,
    val title: String,
    val image: String,
)