package com.jeluchu.core.utils

object BaseUrls {
    const val JIKAN = "https://api.jikan.moe/v4/"
}

object Endpoints {
    const val SCHEDULES = "schedules"
    const val TOP_ANIME = "top/anime"
    const val TOP_MANGA = "top/manga"
    const val TOP_PEOPLE = "top/people"
    const val TOP_CHARACTER = "top/characters"
}

object Routes {
    const val TOP = "/top"
    const val ANIME = "/anime"
    const val MANGA = "/manga"
    const val PAGE = "/{page}"
    const val PEOPLE = "/people"
    const val SCHEDULE = "/schedule"
    const val DIRECTORY = "/directory"
    const val CHARACTER = "/characters"
    const val ANIME_DETAILS = "/anime/{id}"
    const val DAY = "/{day}"
    const val TOP_CHARACTER = "/top/character"
    const val RANKINGS = "/{type}/{filter}/{page}"
}

object TimerKey {
    const val KEY = "key"
    const val RANKING = "ranking"
    const val SCHEDULE = "schedule"
    const val LAST_UPDATED = "lastUpdated"
}

object Collections {
    const val TIMERS = "timers"
    const val SCHEDULES = "schedule"
    const val ANIME_DETAILS = "anime_details"
    const val ANIME_RANKING = "anime_ranking"
    const val MANGA_RANKING = "manga_ranking"
    const val PEOPLE_RANKING = "people_ranking"
    const val CHARACTER_RANKING = "character_ranking"
}