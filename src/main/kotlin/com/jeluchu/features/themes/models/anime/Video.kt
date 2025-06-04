package com.jeluchu.features.themes.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val size: Int? = 0,
    val link: String? = "",
    val resolution: Int? = 0,
    val source: String? = "",
    val nc: Boolean? = false,
    val overlap: String? = "",
    val filename: String? = "",
    val uncen: Boolean? = false,
    val lyrics: Boolean? = false,
    val subbed: Boolean? = false
)