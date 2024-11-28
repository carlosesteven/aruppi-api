package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val generic: String = "",
    val small: String = "",
    val medium: String = "",
    val large: String = "",
    val maximum: String = ""
)