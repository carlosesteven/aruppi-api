package com.jeluchu.core.models.animeflv.lastepisodes

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeEntity(
    var number: Int,
    var image: String,
    var title: String
)
