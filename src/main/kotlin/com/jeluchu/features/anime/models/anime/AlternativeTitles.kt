package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class AlternativeTitles(
    /**
     * Title for anime.
     */
    val title: String = "",

    /**
     * Title type for anime.
     */
    val type: String = ""
)