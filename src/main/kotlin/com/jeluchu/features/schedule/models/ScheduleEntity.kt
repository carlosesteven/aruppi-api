package com.jeluchu.features.schedule.models

import com.jeluchu.core.models.jikan.anime.AnimeData
import com.jeluchu.core.models.jikan.search.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Schedule data class.
 */
@Serializable
data class ScheduleEntity(
    /**
     * Pagination info for request
     */
    @SerialName("pagination")
    val pagination: Pagination? = Pagination(),

    /**
     * Data for anime requested.
     */
    @SerialName("data")
    val data: List<AnimeData>? = emptyList()
)