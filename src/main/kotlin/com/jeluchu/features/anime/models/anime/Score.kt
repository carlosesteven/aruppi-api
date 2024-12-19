package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
    data class Score(
        val percentage: Double,
        val score: Int,
        val votes: Int
    )