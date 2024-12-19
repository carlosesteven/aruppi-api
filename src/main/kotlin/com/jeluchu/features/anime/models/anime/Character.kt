package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    var character: Individual = Individual(),
    var role: String = "",
    var voiceActor: List<Actor> = emptyList()
)