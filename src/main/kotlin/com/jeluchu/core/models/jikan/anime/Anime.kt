package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName

/**
 * Anime data class.
 */
data class Anime(

    /**
     * Data for anime requested.
     */
    @SerialName("data")
    val data: AnimeData
)