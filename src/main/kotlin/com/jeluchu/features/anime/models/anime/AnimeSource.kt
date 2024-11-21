package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class AnimeSource(
    val id: String,
    val source: String
)

