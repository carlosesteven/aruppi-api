package com.jeluchu.features.anime.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class AnimeDirectoryEntity(
    val rank: Int = 0,
    val year: Int = 0,
    var malId: Int = 0,
    val url: String = "",
    var type: String = "",
    var score: String = "",
    var title: String = "",
    var status: String = "",
    val season: String = "",
    var poster: String = "",
    var episodesCount: Int = 0,
    val airing: Boolean = false,
    var genres: List<String> = emptyList()
)