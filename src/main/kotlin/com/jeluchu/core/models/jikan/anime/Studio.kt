package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Studio data class.
 */
@Serializable
data class Studio(
    /**
     * ID associated with MyAnimeList.
     */
    @SerialName("mal_id")
    val malId: Int?,

    /**
     * Name for studio.
     */
    @SerialName("name")
    val name: String?,

    /**
     * Type for studio.
     */
    @SerialName("type")
    val type: String?,

    /**
     * Url for studio.
     */
    @SerialName("url")
    val url: String?
)