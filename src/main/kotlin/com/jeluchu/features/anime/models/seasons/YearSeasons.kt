package com.jeluchu.features.anime.models.seasons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YearSeasons(
    @SerialName("year") val year: Int,
    @SerialName("seasons") val seasons: List<String>
)