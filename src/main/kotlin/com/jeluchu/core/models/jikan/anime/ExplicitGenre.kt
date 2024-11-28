package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * ExplicitGenre data class.
 */
@Serializable
data class ExplicitGenre(
    /**
     * ID associated with MyAnimeList.
     */
    @SerialName("mal_id")
    val malId: Int?,

    /**
     * Name for explicit genre.
     */
    @SerialName("name")
    val name: String?,

    /**
     * Type for explicit genre.
     */
    @SerialName("type")
    val type: String?,

    /**
     * Url for explicit genre.
     */
    @SerialName("url")
    val url: String?
)