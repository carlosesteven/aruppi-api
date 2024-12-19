package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class AiringTime(
    /**
     * Start date airing.
     */
    val from: String = "",

    /**
     * End date airing.
     */
    val to: String = ""
)