package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeInfo(
    val number: Int? = null,
    val seasonNumber: Int? = null,
    val relativeNumber: Int? = null,
    val airdate: String? = null,
    val duration: Int? = null,
    val thumbnail: String? = null,
    val synopsis: MultipleLanguage? = null,
    val titles: MultipleLanguageTitles? = null,
    var score: Double? = null,
    var filler: Boolean? = false,
    var recap: Boolean? = false
)