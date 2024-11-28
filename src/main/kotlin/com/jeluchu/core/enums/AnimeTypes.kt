package com.jeluchu.core.enums

import kotlinx.serialization.Serializable

@Serializable
enum class AnimeTypes {
    TV,
    MOVIE,
    OVA,
    SPECIAL,
    ONA,
    MUSIC,
    CM,
    PV,
    TV_SPECIAL,
}

fun parseType(type: String) = AnimeTypes.entries.firstOrNull { it.name.equals(type, ignoreCase = true) }