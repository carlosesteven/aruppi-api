package com.example.models

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