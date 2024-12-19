package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class ExternalLinks(
    /**
     * Url for trailer.
     */
    val url: String = "",

    /**
     * Name of external info.
     */
    val name: String = ""
)