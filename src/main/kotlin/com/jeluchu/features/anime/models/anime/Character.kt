package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val malId: Int = 0,
    val url: String = "",
    val name: String = "",
    val images: String = "",
    var role: String = "",
    var voiceActor: List<Actor> = emptyList()
)