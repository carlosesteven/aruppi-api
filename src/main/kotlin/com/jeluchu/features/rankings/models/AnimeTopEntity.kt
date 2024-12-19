package com.jeluchu.features.rankings.models

import com.jeluchu.features.anime.models.anime.VideoPromo
import kotlinx.serialization.Serializable

@Serializable
data class AnimeTopEntity(
    val malId: Int? = 0,
    val rank: Int? = 0,
    val score: Float? = 0f,
    val title: String? = "",
    val image: String? = "",
    val url: String? = "",
    val promo: VideoPromo? = VideoPromo(),
    val season: String? = "",
    val year: Int? = 0,
    val airing: Boolean? = false,
    val top: String? = "",
    val type: String? = "",
    val subtype: String? = "",
    val page: Int? = 0
)