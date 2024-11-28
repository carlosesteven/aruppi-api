package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Broadcast data class.
 */
@Serializable
data class Broadcast(
    /**
     * Day in broadcast.
     */
    @SerialName("day")
    val day: String? = "",

    /**
     * String date in broadcast.
     */
    @SerialName("string")
    val string: String? = "",

    /**
     * Time date in broadcast.
     */
    @SerialName("time")
    val time: String? = "",

    /**
     * Timezone in broadcast.
     */
    @SerialName("timezone")
    val timezone: String? = ""
)