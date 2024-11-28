package com.jeluchu.core.enums

import kotlinx.serialization.Serializable

@Serializable
enum class AnimeFilterTypes {
    AIRING,
    UPCOMING,
    BYPOPULARITY,
    FAVORITE,
}

fun parseFilterType(type: String) = AnimeFilterTypes.entries.firstOrNull { it.name.equals(type, ignoreCase = true) }