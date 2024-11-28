package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Title data class.
 */
@Serializable
data class Title(
    /**
     * Title for anime.
     */
    @SerialName("title")
    val title: String?,

    /**
     * Title type for anime.
     */
    @SerialName("type")
    val type: String?
)