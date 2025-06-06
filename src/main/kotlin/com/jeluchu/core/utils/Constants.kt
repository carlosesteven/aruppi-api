package com.jeluchu.core.utils

object BaseUrls {
    const val JIKAN = "https://api.jikan.moe/v4/"
    const val ANIME_THEMES = "https://api.animethemes.moe/"
    const val ANIME_PICTURES = "https://api.anime-pictures.net/api/v3/"
}

object RssUrls {
    // Anitakume Podcast
    const val ANITAKUME = "https://www.ivoox.com/feed_fg_f1660716_filtro_1.xml"

    // Spanish news
    const val SOMOSKUDASAI = "https://somoskudasai.com/feed/"
    const val MANGALATAM = "https://www.mangalatam.com/feeds/posts/default?alt=rss"
    const val CRUNCHYROLL = "https://cr-news-api-service.prd.crunchyrollsvc.com/v1/es-ES/rss"
    const val RAMENPARADOS = "https://ramenparados.com/feed/"
    const val ANMOSUGOI = "https://www.anmosugoi.com/feed/"

    // English news
    const val OTAKUMODE = "https://otakumode.com/news/feed"
    const val MYANIMELIST = "https://myanimelist.net/rss/news.xml"
    const val HONEYSANIME = "https://honeysanime.com/feed/"
    const val ANIMEHUNCH = "https://animehunch.com/feed/"
}

object RssSources {
    const val SOMOSKUDASAI = "Somos Kudasai"
    const val MANGALATAM = "MangaLatam"
    const val CRUNCHYROLL = "Crunchyroll"
    const val RAMENPARADOS = "Ramen para dos"
    const val ANMOSUGOI = "Anmo Sugoi"
    const val OTAKUMODE = "Tokyo Otaku Mode News"
    const val MYANIMELIST = "MyAnimeList"
    const val HONEYSANIME = "Honey's anime"
    const val ANIMEHUNCH = "My Anime For Life"
}

object Endpoints {
    const val POSTS = "posts"
    const val ANIME = "anime"
    const val SCHEDULES = "schedules"
    const val TOP_ANIME = "top/anime"
    const val TOP_MANGA = "top/manga"
    const val TOP_PEOPLE = "top/people"
    const val TOP_CHARACTER = "top/characters"
    const val LAST_EPISODES = "list/latest-episodes"
}

object Routes {
    const val ES = "/es"
    const val EN = "/en"
    const val TOP = "/top"
    const val NEWS = "/news"
    const val ANIME = "/anime"
    const val MANGA = "/manga"
    const val PEOPLE = "/people"
    const val SEARCH = "/search"
    const val GALLERY = "/gallery"
    const val SCHEDULE = "/schedule"
    const val LAST_POST = "/lastPosts"
    const val DIRECTORY = "/directory"
    const val ANITAKUME = "/anitakume"
    const val CHARACTER = "/characters"
    const val LAST_EPISODES = "/lastEpisodes"
    const val ID = "/{id}"
    const val TYPE = "/{type}"
    const val SEASON = "/{year}/{season}"
    const val DAY = "/{day}"
    const val THEMES = "/themes"
}

object TimerKey {
    const val KEY = "key"
    const val SCHEDULE = "schedule"
    const val LAST_UPDATED = "lastUpdated"
    const val ANIME_TYPE = "anime_"
    const val THEMES = "themes_"
    const val LAST_EPISODES = "last_episodes"
}

object Collections {
    const val TIMERS = "timers"
    const val NEWS_ES = "news_es"
    const val NEWS_EN = "news_en"
    const val SCHEDULES = "schedule"
    const val ANITAKUME = "anitakume"
    const val ANIME_THEMES = "anime_themes"
    const val ARTISTS_INDEX = "artists_index"
    const val ANIME_DETAILS = "anime_details"
    const val LAST_EPISODES = "last_episodes"
    const val ANIME_RANKING = "anime_ranking"
    const val MANGA_RANKING = "manga_ranking"
    const val PEOPLE_RANKING = "people_ranking"
    const val CHARACTER_RANKING = "character_ranking"
    const val ANIME_PICTURES_QUERY = "anime_pictures_query"
    const val ANIME_PICTURES_RECENT = "anime_pictures_recent"
}