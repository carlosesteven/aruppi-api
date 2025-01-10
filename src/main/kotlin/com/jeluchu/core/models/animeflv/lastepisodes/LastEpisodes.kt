package com.jeluchu.core.models.animeflv.lastepisodes

import kotlinx.serialization.Serializable

@Serializable
data class LastEpisodes(
    val data: List<LastEpisodeData>?,
    val success: Boolean?
)