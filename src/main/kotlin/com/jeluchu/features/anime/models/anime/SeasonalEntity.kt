package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class SeasonalEntity(
    var station: String? = null,
    var year: Int? = null
)