package com.jeluchu.core.utils

import com.jeluchu.core.enums.Season
import java.util.Calendar
import java.util.Locale

object SeasonCalendar {
    private val calendar: Calendar by lazy {
        Calendar.getInstance(Locale.getDefault())
    }

    val currentYear = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)

    val currentSeason = when (month) {
        0, 1, 11 -> Season.WINTER
        2, 3, 4 -> Season.SPRING
        5, 6, 7 -> Season.SUMMER
        8, 9, 10 -> Season.FALL
        else -> Season.SPRING
    }
}