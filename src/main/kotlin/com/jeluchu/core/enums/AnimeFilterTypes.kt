package com.jeluchu.core.enums

import kotlinx.serialization.Serializable

@Serializable
enum class AnimeFilterTypes {
    AIRING,
    UPCOMING,
    BYPOPULARITY,
    FAVORITE,
}

val animeFilterTypesErrorList = AnimeFilterTypes.entries.joinToString(", ") { it.name.lowercase() }
fun parseAnimeFilterType(type: String) = AnimeFilterTypes.entries.firstOrNull { it.name.equals(type, ignoreCase = true) }