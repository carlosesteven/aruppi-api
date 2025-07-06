package com.jeluchu.core.enums

enum class Season {
    WINTER, SPRING, SUMMER, FALL
}

val seasonsErrorList = AnimeTypes.entries.joinToString(", ") { it.name.lowercase() }
fun parseSeasons(type: String) = Season.entries.firstOrNull { it.name.equals(type, ignoreCase = true) }