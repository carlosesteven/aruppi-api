package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * External data class.
 */
@Serializable
data class External(
    /**
     * Name of external info.
     */
    @SerialName("name")
    val name: String?,

    /**
     * Url of external info.
     */
    @SerialName("url")
    val url: String?
)