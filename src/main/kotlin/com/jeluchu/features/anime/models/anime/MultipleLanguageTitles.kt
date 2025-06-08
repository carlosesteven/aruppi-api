package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class MultipleLanguageTitles(
    val es: String? = null,
    val en: String? = null,
    val jp: String? = null,
    val romaji_jp: String? = null,
)