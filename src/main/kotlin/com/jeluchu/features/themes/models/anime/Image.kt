package com.jeluchu.features.themes.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val id: Int?,
    val facet: String?,
    val link: String?,
    val path: String?
)