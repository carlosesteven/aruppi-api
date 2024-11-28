package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Genre data class.
 */
@Serializable
data class Genre(
    /**
     * ID associated with MyAnimeList.
     */
    @SerialName("mal_id")
    val malId: Int?,

    /**
     * Name for genre.
     */
    @SerialName("name")
    val name: String?,

    /**
     * Type for genre.
     */
    @SerialName("type")
    val type: String?,

    /**
     * Url for genre.
     */
    @SerialName("url")
    val url: String?
)