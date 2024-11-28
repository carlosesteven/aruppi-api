package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Demographic data class.
 */
@Serializable
data class Demographic(
    /**
     * ID associated with MyAnimeList.
     */
    @SerialName("mal_id")
    val malId: Int?,

    /**
     * Name for demographic.
     */
    @SerialName("name")
    val name: String?,

    /**
     * Type for demographic.
     */
    @SerialName("type")
    val type: String?,

    /**
     * Url for demographic.
     */
    @SerialName("url")
    val url: String?
)