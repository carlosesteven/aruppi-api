package com.example.models

import kotlinx.serialization.Serializable

@Serializable
open class Themes(
    /**
     * List of endings.
     */
    val endings: List<String> = emptyList(),

    /**
     * List of openings.
     */
    val openings: List<String> = emptyList()
)