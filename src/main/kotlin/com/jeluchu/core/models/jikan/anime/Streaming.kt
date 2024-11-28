package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Streaming data class.
 */
@Serializable
data class Streaming(
    /**
     * Name of streaming info.
     */
    @SerialName("name")
    val name: String?,

    /**
     * Url of streaming info.
     */
    @SerialName("url")
    val url: String?
)