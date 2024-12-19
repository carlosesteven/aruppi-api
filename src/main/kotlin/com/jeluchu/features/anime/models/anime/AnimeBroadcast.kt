package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeBroadcast(
    /**
     * Day in broadcast.
     */
    val day: String = "",

    /**
     * Time date in broadcast.
     */
    val time: String = "",

    /**
     * Timezone in broadcast.
     */
    val timezone: String = ""
)