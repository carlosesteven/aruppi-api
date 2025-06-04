package com.jeluchu.features.themes.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimesEntity(
    val year: Int? = null,
    val slug: String? = null,
    val name: String? = null,
    val image: String? = null,
    val season: String? = null,
)