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

val animeTypesErrorList = AnimeTypes.entries.joinToString(", ") { it.name.lowercase() }
fun parseAnimeType(type: String) = AnimeTypes.entries.firstOrNull { it.name.equals(type, ignoreCase = true) }