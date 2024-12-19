package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class Staff(
    var person: Individual = Individual(),
    var positions: List<String> = emptyList()
)