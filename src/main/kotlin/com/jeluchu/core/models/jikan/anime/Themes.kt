package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Themes data class.
 */
@Serializable
data class Themes(
    /**
     * ID associated with MyAnimeList.
     */
    @SerialName("mal_id")
    val malId: Int?,

    /**
     * Name for themes.
     */
    @SerialName("name")
    val name: String?,

    /**
     * Type for themes.
     */
    @SerialName("type")
    val type: String?,

    /**
     * Url for themes.
     */
    @SerialName("url")
    val url: String?
)