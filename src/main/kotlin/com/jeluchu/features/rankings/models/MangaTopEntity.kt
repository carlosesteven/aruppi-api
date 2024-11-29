package com.jeluchu.features.rankings.models

import kotlinx.serialization.Serializable

@Serializable
data class MangaTopEntity(
    val malId: Int? = 0,
    val rank: Int? = 0,
    val score: Double? = 0.0,
    val title: String? = "",
    val image: String? = "",
    val url: String? = "",
    val volumes: Int? = 0,
    val chapters: Int? = 0,
    val status: String? = "",
    val top: String? = "",
    val type: String? = "",
    val subtype: String? = "",
    val page: Int? = 0
)