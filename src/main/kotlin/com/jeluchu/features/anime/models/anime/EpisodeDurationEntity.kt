package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDurationEntity(
    var unit: String? = null,
    var value: Int? = null
)