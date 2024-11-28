package com.jeluchu.features.anime.mappers

import com.example.models.*
import com.jeluchu.core.extensions.*
import com.jeluchu.features.anime.models.anime.Images
import com.jeluchu.features.anime.models.directory.AnimeDirectoryEntity
import com.jeluchu.features.schedule.models.DayEntity
import org.bson.Document

fun documentToAnimeDirectoryEntity(doc: Document) = AnimeDirectoryEntity(
    rank = doc.getIntSafe("rank"),
    year = doc.getIntSafe("year"),
    url = doc.getStringSafe("url"),
    malId = doc.getIntSafe("malId"),
    type = doc.getStringSafe("type"),
    score = doc.getStringSafe("score"),
    title = doc.getStringSafe("title"),
    status = doc.getStringSafe("status"),
    season = doc.getStringSafe("season"),
    poster = doc.getStringSafe("poster"),
    airing = doc.getBooleanSafe("airing"),
    genres = doc.getListSafe<String>("genres"),
    episodesCount = doc.getIntSafe("episodesCount")
)

fun documentToMoreInfoEntity(doc: Document): MoreInfoEntity {
    return MoreInfoEntity(
        id = doc.getLongSafe("id"),
        malId = doc.getIntSafe("malId"),
        title = doc.getStringSafe("title"),
        poster = doc.getStringSafe("poster"),
        cover = doc.getStringSafe("cover"),
        genres = doc.getListSafe<String>("genres"),
        synopsis = doc.getStringSafe("synopsis"),
        episodes = doc.getListSafe<Document>("episodes").map { documentToMergedEpisode(it) },
        episodesCount = doc.getIntSafe("episodesCount", 0),
        score = doc.getStringSafe("score"),
        staff = doc.getListSafe<Document>("staff").map { documentToStaff(it) },
        characters = doc.getListSafe<Document>("characters").map { documentToCharacter(it) },
        status = doc.getStringSafe("status"),
        type = doc.getStringSafe("type"),
        url = doc.getStringSafe("url"),
        promo = doc.getDocumentSafe("promo")?.let { documentToVideoPromo(it) } ?: VideoPromo(),
        source = doc.getStringSafe("source"),
        duration = doc.getStringSafe("duration"),
        rank = doc.getIntSafe("rank", 0),
        titles = doc.getListSafe<Document>("titles").map { documentToAlternativeTitles(it) },
        airing = doc.getBooleanSafe("airing"),
        aired = doc.getDocumentSafe("aired")?.let { documentToAiringTime(it) } ?: AiringTime(),
        broadcast = doc.getDocumentSafe("broadcast")?.let { documentToAnimeBroadcast(it) } ?: AnimeBroadcast(),
        season = doc.getStringSafe("season"),
        year = doc.getIntSafe("year", 0),
        external = doc.getListSafe<Document>("external").map { documentToExternalLinks(it) },
        streaming = doc.getListSafe<Document>("streaming").map { documentToExternalLinks(it) },
        studios = doc.getListSafe<Document>("studios").map { documentToCompanies(it) },
        licensors = doc.getListSafe<Document>("licensors").map { documentToCompanies(it) },
        producers = doc.getListSafe<Document>("producers").map { documentToCompanies(it) },
        theme = doc.getDocumentSafe("theme")?.let { documentToThemes(it) } ?: Themes(),
        relations = doc.getListSafe<Document>("relations").map { documentToRelated(it) },
        stats = doc.getDocumentSafe("stats")?.let { documentToStatistics(it) } ?: Statistics(),
        gallery = doc.getListSafe<Document>("gallery").map { documentToImageMediaEntity(it) },
        episodeSource = doc.getStringSafe("episodeSource")
    )
}

fun documentToActor(doc: Document): Actor {
    return Actor(
        person = doc.getDocumentSafe("person")?.let { documentToIndividual(it) } ?: Individual(),
        language = doc.getStringSafe("language")
    )
}

fun documentToAiringTime(doc: Document): AiringTime {
    return AiringTime(
        from = doc.getStringSafe("from"),
        to = doc.getStringSafe("to")
    )
}

fun documentToAlternativeTitles(doc: Document): AlternativeTitles {
    return AlternativeTitles(
        title = doc.getStringSafe("title"),
        type = doc.getStringSafe("type")
    )
}

fun documentToAnimeBroadcast(doc: Document): AnimeBroadcast {
    return AnimeBroadcast(
        day = doc.getStringSafe("day"),
        time = doc.getStringSafe("time"),
        timezone = doc.getStringSafe("timezone")
    )
}

fun documentToAnimeSource(doc: Document): AnimeSource {
    return AnimeSource(
        id = doc.getStringSafe("id"),
        source = doc.getStringSafe("source")
    )
}

fun documentToCharacter(doc: Document): Character {
    return Character(
        character = doc.getDocumentSafe("character")?.let { documentToIndividual(it) } ?: Individual(),
        role = doc.getStringSafe("role"),
        voiceActor = doc.getListSafe<Document>("voiceActor").map { documentToActor(it) }
    )
}

fun documentToCompanies(doc: Document): Companies {
    return Companies(
        malId = doc.getIntSafe("malId", 0),
        name = doc.getStringSafe("name"),
        type = doc.getStringSafe("type"),
        url = doc.getStringSafe("url")
    )
}

fun documentToExternalLinks(doc: Document): ExternalLinks {
    return ExternalLinks(
        url = doc.getStringSafe("url"),
        name = doc.getStringSafe("name")
    )
}

fun documentToImageMediaEntity(doc: Document): ImageMediaEntity {
    return ImageMediaEntity(
        media = doc.getStringSafe("media"),
        thumbnail = doc.getStringSafe("thumbnail"),
        width = doc.getIntSafe("width", 0),
        height = doc.getIntSafe("height", 0),
        url = doc.getStringSafe("url")
    )
}

fun documentToImages(doc: Document): Images {
    return Images(
        generic = doc.getStringSafe("generic"),
        small = doc.getStringSafe("small"),
        medium = doc.getStringSafe("medium"),
        large = doc.getStringSafe("large"),
        maximum = doc.getStringSafe("maximum")
    )
}

fun documentToIndividual(doc: Document): Individual {
    return Individual(
        malId = doc.getIntSafe("malId", 0),
        url = doc.getStringSafe("url"),
        name = doc.getStringSafe("name"),
        images = doc.getStringSafe("images")
    )
}

fun documentToMergedEpisode(doc: Document): MergedEpisode {
    return MergedEpisode(
        number = doc.getIntSafe("number", 0),
        ids = doc.getListSafe<Document>("ids").map { documentToAnimeSource(it) }.toMutableList(),
        nextEpisodeDate = doc.getStringSafe("nextEpisodeDate")
    )
}

fun documentToRelated(doc: Document): Related {
    return Related(
        entry = doc.getListSafe<Document>("entry").map { documentToCompanies(it) },
        relation = doc.getStringSafe("relation")
    )
}

fun documentToScore(doc: Document): Score {
    return Score(
        percentage = when (val value = doc["percentage"]) {
            is Double -> value
            is Int -> value.toDouble()
            else -> 0.0
        },
        score = doc.getIntSafe("score", 0),
        votes = doc.getIntSafe("votes", 0)
    )
}

fun documentToStaff(doc: Document): Staff {
    return Staff(
        person = doc.get("person", Document::class.java)?.let { documentToIndividual(it) } ?: Individual(),
        positions = doc.getListSafe<String>("positions")
    )
}

fun documentToStatistics(doc: Document): Statistics {
    return Statistics(
        completed = doc.getIntSafe("completed"),
        dropped = doc.getIntSafe("dropped"),
        onHold = doc.getIntSafe("onHold"),
        planToWatch = doc.getIntSafe("planToWatch"),
        scores = doc.getListSafe<Document>("scores").map { documentToScore(it) },
        total = doc.getIntSafe("total"),
        watching = doc.getIntSafe("watching")
    )
}

fun documentToThemes(doc: Document): Themes {
    return Themes(
        endings = doc.getListSafe<String>("endings"),
        openings = doc.getListSafe<String>("openings")
    )
}

fun documentToVideoPromo(doc: Document): VideoPromo {
    return VideoPromo(
        embedUrl = doc.getStringSafe("embedUrl"),
        url = doc.getStringSafe("url"),
        youtubeId = doc.getStringSafe("youtubeId"),
        images = doc.get("images", Document::class.java)?.let { documentToImages(it) } ?: Images()
    )
}

fun documentToScheduleDayEntity(doc: Document) = DayEntity(
    day = doc.getStringSafe("day"),
    malId = doc.getIntSafe("malId"),
    image = doc.getStringSafe("image"),
    title = doc.getStringSafe("title")
)