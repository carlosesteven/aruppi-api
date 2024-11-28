package com.jeluchu.core.utils

object BaseUrls {
    const val JIKAN = "https://api.jikan.moe/v4/"
}

object Endpoints {
    const val SCHEDULES = "schedules"
    const val TOP_ANIME = "top/anime"
}


object Routes {
    const val TOP = "/top"
    const val ANIME = "/anime"
    const val SCHEDULE = "/schedule"
    const val DIRECTORY = "/directory"
    const val TOP_MANGA = "/top/manga"
    const val TOP_PEOPLE = "/top/people"
    const val ANIME_DETAILS = "/anime/{id}"
    const val SCHEDULE_DAY = "/schedule/{day}"
    const val TOP_CHARACTER = "/top/character"
    const val TOP_ANIME = "/{type}/{filter}/{page}"
}

object TimerKey {
    const val KEY = "key"
    const val RANKING = "ranking"
    const val SCHEDULE = "schedule"
    const val LAST_UPDATED = "lastUpdated"
}