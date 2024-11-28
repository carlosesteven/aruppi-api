package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Licensor data class.
 */
@Serializable
data class Licensor(
    /**
     * ID associated with MyAnimeList.
     */
    @SerialName("mal_id")
    val malId: Int?,

    /**
     * Name for licensor.
     */
    @SerialName("name")
    val name: String?,

    /**
     * Type for licensor.
     */
    @SerialName("type")
    val type: String?,

    /**
     * Url for licensor.
     */
    @SerialName("url")
    val url: String?
)