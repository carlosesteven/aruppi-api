package com.jeluchu.core.enums

import kotlinx.serialization.Serializable

@Serializable
enum class MangaTypes {
    MANGA,
    NOVEL,
    LIGHTNOVEL,
    ONESHOT,
    DOUJIN,
    MANHWA,
    MANHUA
}

val mangaTypesErrorList = MangaTypes.entries.joinToString(", ") { it.name.lowercase() }
fun parseMangaType(type: String) = MangaTypes.entries.firstOrNull { it.name.equals(type, ignoreCase = true) }