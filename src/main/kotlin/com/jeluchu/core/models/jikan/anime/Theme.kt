package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Theme data class.
 */
@Serializable
data class Theme(
    /**
     * List of endings.
     */
    @SerialName("endings")
    val endings: List<String>? = emptyList(),

    /**
     * List of openings.
     */
    @SerialName("openings")
    val openings: List<String>? = emptyList()
)