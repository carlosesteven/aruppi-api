package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class MergedEpisode(
    var number: Int,
    var ids: MutableList<AnimeSource> = mutableListOf(),
    var nextEpisodeDate: String = ""
)
