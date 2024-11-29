package com.jeluchu.core.enums

import kotlinx.serialization.Serializable

@Serializable
enum class MangaFilterTypes {
    PUBLISHING,
    UPCOMING,
    BYPOPULARITY,
    FAVORITE,
}

val mangaFilterTypesErrorList = MangaFilterTypes.entries.joinToString(", ") { it.name.lowercase() }
fun parseMangaFilterType(type: String) = MangaFilterTypes.entries.firstOrNull { it.name.equals(type, ignoreCase = true) }