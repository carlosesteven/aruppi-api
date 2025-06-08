package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class MultipleLanguageLists(
    val es: List<String>? = null,
    val en: List<String>? = null
)