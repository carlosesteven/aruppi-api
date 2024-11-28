package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName

data class Score(
    @SerialName("percentage")
    val percentage: Double,

    @SerialName("score")
    val score: Int,

    @SerialName("votes")
    val votes: Int
)