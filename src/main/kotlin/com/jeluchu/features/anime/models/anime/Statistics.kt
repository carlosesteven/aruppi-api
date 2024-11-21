package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Statistics(
    val completed: Int? = null,
    val dropped: Int? = null,
    val onHold: Int? = null,
    val planToWatch: Int? = null,
    val scores: List<Score>? = emptyList(),
    val total: Int? = null,
    val watching: Int? = null
)