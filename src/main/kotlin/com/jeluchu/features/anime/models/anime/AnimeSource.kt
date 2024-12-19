package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeSource(
    val id: String,
    val source: String
)

