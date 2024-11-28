package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Entry data class.
 */
@Serializable
data class Entry(
    /**
     * ID associated with MyAnimeList.
     */
    @SerialName("mal_id")
    val malId: Int?,

    /**
     * Name for entry.
     */
    @SerialName("name")
    val name: String?,

    /**
     * Type for entry.
     */
    @SerialName("type")
    val type: String?,

    /**
     * Url for entry.
     */
    @SerialName("url")
    val url: String?
)