package com.jeluchu.features.news.models

import kotlinx.serialization.Serializable

@Serializable
data class NewEntity(
    val source: String,
    val sourceDescription: String,
    val link: String,
    val title: String,
    val date: String,
    val description: String,
    val content: String,
    val image: String,
    val categories: List<String>
)