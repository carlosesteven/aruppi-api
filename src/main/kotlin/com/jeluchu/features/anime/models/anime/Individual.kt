package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class Individual(
    val malId: Int = 0,
    val url: String = "",
    val name: String = "",
    val images: String = ""
)