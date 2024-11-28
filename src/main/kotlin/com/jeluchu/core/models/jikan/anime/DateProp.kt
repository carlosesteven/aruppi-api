package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * DateProp data class.
 */
@Serializable
data class DateProp(
    /**
     * Day in date.
     */
    @SerialName("day")
    val day: Int? = 0,

    /**
     * Month in date.
     */
    @SerialName("month")
    val month: Int? = 0,

    /**
     * Year in date.
     */
    @SerialName("year")
    val year: Int? = 0
)