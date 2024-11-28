package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Producer data class.
 */
@Serializable
data class Producer(
    /**
     * ID associated with MyAnimeList.
     */
    @SerialName("mal_id")
    val malId: Int?,

    /**
     * Name for producer.
     */
    @SerialName("name")
    val name: String?,

    /**
     * Type for producer.
     */
    @SerialName("type")
    val type: String?,

    /**
     * Url for producer.
     */
    @SerialName("url")
    val url: String?
)