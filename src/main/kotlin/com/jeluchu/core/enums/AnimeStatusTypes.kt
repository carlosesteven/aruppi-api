package com.jeluchu.core.enums

import kotlinx.serialization.Serializable

@Serializable
enum class AnimeStatusTypes {
    FINISHED, ONGOING, UPCOMING, UNKNOWN
}

val animeStatusTypesErrorList = AnimeStatusTypes.entries.joinToString(", ") { it.name.lowercase() }
fun parseAnimeStatusType(type: String) = AnimeStatusTypes.entries.firstOrNull { it.name.equals(type, ignoreCase = true) }