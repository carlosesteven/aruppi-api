package com.example.models

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