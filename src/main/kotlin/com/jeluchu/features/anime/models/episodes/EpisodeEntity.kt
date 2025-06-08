package com.jeluchu.features.anime.models.episodes

import com.jeluchu.core.models.jikan.anime.AnimeData
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeEntity(
    val malId: Int = 0,
    val title: String?,
    val score: String?,
    val image: String?,
    val day: String?,
    val time: String?,
    val timezone: String?,
)