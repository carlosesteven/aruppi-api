package com.jeluchu.core.utils

object BaseUrls {
    const val JIKAN = "https://api.jikan.moe/v4/"
    const val ANIME_FLV = "https://animeflv.ahmedrangel.com/api/"
}

object Endpoints {
    const val FULL = "full"
    const val ANIME = "anime/"
    const val SCHEDULES = "schedules"
    const val TOP_ANIME = "top/anime"
    const val TOP_MANGA = "top/manga"
    const val TOP_PEOPLE = "top/people"
    const val STATISTICS = "statistics"
    const val CHARACTERS = "characters"
    const val TOP_CHARACTER = "top/characters"
    const val LAST_EPISODES = "list/latest-episodes"
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
    const val LAST_EPISODES = "/lastEpisodes"
    const val ID = "/{id}"
    const val ANIME_TYPE = "/{type}"
    const val DAY = "/{day}"
    const val TOP_CHARACTER = "/top/character"
    const val RANKINGS = "/{type}/{filter}/{page}"
}

object TimerKey {
    const val KEY = "key"
    const val RANKING = "ranking"
    const val SCHEDULE = "schedule"
    const val LAST_UPDATED = "lastUpdated"
    const val ANIME_TYPE = "anime_"
    const val LAST_EPISODES = "last_episodes"
}

object Collections {
    const val TIMERS = "timers"
    const val SCHEDULES = "schedule"
    const val ANIME_TYPE = "anime_"
    const val ANIME_DETAILS = "anime_details"
    const val LAST_EPISODES = "last_episodes"
    const val ANIME_RANKING = "anime_ranking"
    const val MANGA_RANKING = "manga_ranking"
    const val PEOPLE_RANKING = "people_ranking"
    const val ANIME_DIRECTORY = "anime_directory"
    const val CHARACTER_RANKING = "character_ranking"
}