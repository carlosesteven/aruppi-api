package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class OtherTitlesEntity(
    var synonyms: List<String>? = null,
    var abbreviatedTitles: List<String>? = null
)