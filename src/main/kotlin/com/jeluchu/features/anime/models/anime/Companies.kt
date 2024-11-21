package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Companies(
    /**
     * ID associated with MyAnimeList.
     */
    val malId: Int = 0,

    /**
     * Name for company.
     */
    val name: String = "",

    /**
     * Type for company.
     */
    val type: String = "",

    /**
     * Url for company.
     */
    val url: String = ""
)