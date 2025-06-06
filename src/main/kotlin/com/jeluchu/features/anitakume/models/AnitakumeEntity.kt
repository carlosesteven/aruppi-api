package com.jeluchu.features.anitakume.models

import kotlinx.serialization.Serializable

@Serializable
data class AnitakumeEntity(
    val image: String,
    val title: String,
    val description: String,
    val date: String,
    val link: String,
    val audio: String,
    val duration: String
)