package com.jeluchu.features.themes.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeVideoTheme(
    val id: Int? = 0,
    val type: String? = "",
    val slug: String? = "",
    val sequence: Int? = 0,
    val entries: List<AnimeThemeEntry>? = emptyList(),
)