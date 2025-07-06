package com.jeluchu.features.anime.models.seasons

import kotlinx.serialization.Serializable

@Serializable
data class SeasonAnimeEntity(
    val malId: Int,
    val title: String,
    val image: String,
    val score: String,
)