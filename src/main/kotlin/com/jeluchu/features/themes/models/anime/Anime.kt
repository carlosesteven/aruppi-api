package com.jeluchu.features.themes.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    val id: Int? = 0,
    val image: String? = "",
    val name: String? = "",
    val season: String? = "",
    val slug: String? = "",
    val synopsis: String? = "",
    val year: Int? = 0,
    val themes: List<AnimeVideoTheme>? = emptyList()
)