package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class MultipleLanguage(
    val es: String? = null,
    val en: String? = null
)