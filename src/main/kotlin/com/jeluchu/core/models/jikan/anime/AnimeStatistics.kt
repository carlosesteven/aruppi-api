package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName

data class AnimeStatistics(
    @SerialName("data")
    val data: Statistics?
) {
    data class Statistics(
        @SerialName("completed")
        val completed: Int?,

        @SerialName("dropped")
        val dropped: Int?,

        @SerialName("on_hold")
        val onHold: Int?,

        @SerialName("plan_to_watch")
        val planToWatch: Int?,

        @SerialName("scores")
        val scores: List<Score>?,

        @SerialName("total")
        val total: Int?,

        @SerialName("watching")
        val watching: Int?
    )
}