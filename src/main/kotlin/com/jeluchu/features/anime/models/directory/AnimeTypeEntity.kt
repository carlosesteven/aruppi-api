package com.jeluchu.features.anime.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class AnimeTypeEntity(
    val malId: Int? = 0,
    val type: String? = "",
    val year: Int? = 0,
    val season: String? = "",
    val title: String? = "",
    val image: String? = "",
    val score: String? = ""
)