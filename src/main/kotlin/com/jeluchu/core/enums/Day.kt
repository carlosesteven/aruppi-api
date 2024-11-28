package com.jeluchu.core.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY,
}

fun parseDay(day: String) = Day.entries.firstOrNull { it.name.equals(day, ignoreCase = true) }