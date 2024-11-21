package com.jeluchu.features.anime.mappers

import com.example.models.*
import org.bson.Document

fun documentToMoreInfoEntity(doc: Document): MoreInfoEntity {
    return MoreInfoEntity(
        id = doc.getLong("id") ?: 0,
        malId = doc.getInteger("malId", 0) ?: 0,
        title = doc.getString("title") ?: "",
        poster = doc.getString("poster") ?: "",
        cover = doc.getString("cover") ?: "",
        genres = doc.getList("genres", String::class.java) ?: emptyList(),
        synopsis = doc.getString("synopsis") ?: "",
        episodes = doc.getList("episodes", Document::class.java)?.map { documentToMergedEpisode(it) } ?: emptyList(),
        episodesCount = doc.getInteger("episodesCount", 0) ?: 0,
        score = doc.getString("score") ?: "",
        staff = doc.getList("staff", Document::class.java)?.map { documentToStaff(it) } ?: emptyList(),
        characters = doc.getList("characters", Document::class.java)?.map { documentToCharacter(it) } ?: emptyList(),
        status = doc.getString("status") ?: "",
        type = doc.getString("type") ?: "",
        url = doc.getString("url") ?: "",
        promo = doc.get("promo", Document::class.java)?.let { documentToVideoPromo(it) } ?: VideoPromo(),
        source = doc.getString("source") ?: "",
        duration = doc.getString("duration") ?: "",
        rank = doc.getInteger("rank", 0) ?: 0,
        titles = doc.getList("titles", Document::class.java)?.map { documentToAlternativeTitles(it) } ?: emptyList(),
        airing = doc.getBoolean("airing", false) ?: false,
        aired = doc.get("aired", Document::class.java)?.let { documentToAiringTime(it) } ?: AiringTime(),
        broadcast = doc.get("broadcast", Document::class.java)?.let { documentToAnimeBroadcast(it) } ?: AnimeBroadcast(),
        season = doc.getString("season") ?: "",
        year = doc.getInteger("year", 0),
        external = doc.getList("external", Document::class.java)?.map { documentToExternalLinks(it) } ?: emptyList(),
        streaming = doc.getList("streaming", Document::class.java)?.map { documentToExternalLinks(it) } ?: emptyList(),
        studios = doc.getList("studios", Document::class.java)?.map { documentToCompanies(it) } ?: emptyList(),
        licensors = doc.getList("licensors", Document::class.java)?.map { documentToCompanies(it) } ?: emptyList(),
        producers = doc.getList("producers", Document::class.java)?.map { documentToCompanies(it) } ?: emptyList(),
        theme = doc.get("theme", Document::class.java)?.let { documentToThemes(it) } ?: Themes(),
        relations = doc.getList("relations", Document::class.java)?.map { documentToRelated(it) } ?: emptyList(),
        stats = doc.get("stats", Document::class.java)?.let { documentToStatistics(it) } ?: Statistics(),
        gallery = doc.getList("gallery", Document::class.java)?.map { documentToImageMediaEntity(it) } ?: emptyList(),
        episodeSource = doc.getString("episodeSource") ?: ""
    )
}

fun documentToActor(doc: Document): Actor {
    return Actor(
        person = doc.get("person", Document::class.java)?.let { documentToIndividual(it) } ?: Individual(),
        language = doc.getString("language") ?: ""
    )
}

fun documentToAiringTime(doc: Document): AiringTime {
    return AiringTime(
        from = doc.getString("from") ?: "",
        to = doc.getString("to") ?: ""
    )
}

fun documentToAlternativeTitles(doc: Document): AlternativeTitles {
    return AlternativeTitles(
        title = doc.getString("title") ?: "",
        type = doc.getString("type") ?: ""
    )
}

fun documentToAnimeBroadcast(doc: Document): AnimeBroadcast {
    return AnimeBroadcast(
        day = doc.getString("day") ?: "",
        time = doc.getString("time") ?: "",
        timezone = doc.getString("timezone") ?: ""
    )
}

fun documentToAnimeSource(doc: Document): AnimeSource {
    return AnimeSource(
        id = doc.getString("id") ?: "",
        source = doc.getString("source") ?: ""
    )
}

fun documentToCharacter(doc: Document): Character {
    return Character(
        character = doc.get("character", Document::class.java)?.let { documentToIndividual(it) } ?: Individual(),
        role = doc.getString("role") ?: "",
        voiceActor = doc.getList("voiceActor", Document::class.java)?.map { documentToActor(it) } ?: emptyList()
    )
}

fun documentToCompanies(doc: Document): Companies {
    return Companies(
        malId = doc.getInteger("malId", 0),
        name = doc.getString("name") ?: "",
        type = doc.getString("type") ?: "",
        url = doc.getString("url") ?: ""
    )
}

fun documentToExternalLinks(doc: Document): ExternalLinks {
    return ExternalLinks(
        url = doc.getString("url") ?: "",
        name = doc.getString("name") ?: ""
    )
}

fun documentToImageMediaEntity(doc: Document): ImageMediaEntity {
    return ImageMediaEntity(
        media = doc.getString("media") ?: "",
        thumbnail = doc.getString("thumbnail") ?: "",
        width = doc.getInteger("width", 0),
        height = doc.getInteger("height", 0),
        url = doc.getString("url") ?: ""
    )
}

fun documentToImages(doc: Document): Images {
    return Images(
        generic = doc.getString("generic") ?: "",
        small = doc.getString("small") ?: "",
        medium = doc.getString("medium") ?: "",
        large = doc.getString("large") ?: "",
        maximum = doc.getString("maximum") ?: ""
    )
}

fun documentToIndividual(doc: Document): Individual {
    return Individual(
        malId = doc.getInteger("malId", 0),
        url = doc.getString("url") ?: "",
        name = doc.getString("name") ?: "",
        images = doc.getString("images") ?: ""
    )
}

fun documentToMergedEpisode(doc: Document): MergedEpisode {
    return MergedEpisode(
        number = doc.getInteger("number", 0),
        ids = doc.getList("ids", Document::class.java)?.map { documentToAnimeSource(it) }?.toMutableList() ?: mutableListOf(),
        nextEpisodeDate = doc.getString("nextEpisodeDate") ?: ""
    )
}

fun documentToRelated(doc: Document): Related {
    return Related(
        entry = doc.getList("entry", Document::class.java)?.map { documentToCompanies(it) } ?: emptyList(),
        relation = doc.getString("relation") ?: ""
    )
}

fun documentToScore(doc: Document): Score {
    return Score(
        percentage = when (val value = doc["percentage"]) {
            is Double -> value
            is Int -> value.toDouble()
            else -> 0.0
        },
        score = doc.getInteger("score", 0),
        votes = doc.getInteger("votes", 0)
    )
}

fun documentToStaff(doc: Document): Staff {
    return Staff(
        person = doc.get("person", Document::class.java)?.let { documentToIndividual(it) } ?: Individual(),
        positions = doc.getList("positions", String::class.java) ?: emptyList()
    )
}

fun documentToStatistics(doc: Document): Statistics {
    return Statistics(
        completed = doc.getInteger("completed"),
        dropped = doc.getInteger("dropped"),
        onHold = doc.getInteger("onHold"),
        planToWatch = doc.getInteger("planToWatch"),
        scores = doc.getList("scores", Document::class.java)?.map { documentToScore(it) } ?: emptyList(),
        total = doc.getInteger("total"),
        watching = doc.getInteger("watching")
    )
}

fun documentToThemes(doc: Document): Themes {
    return Themes(
        endings = doc.getList("endings", String::class.java) ?: emptyList(),
        openings = doc.getList("openings", String::class.java) ?: emptyList()
    )
}

fun documentToVideoPromo(doc: Document): VideoPromo {
    return VideoPromo(
        embedUrl = doc.getString("embedUrl") ?: "",
        url = doc.getString("url") ?: "",
        youtubeId = doc.getString("youtubeId") ?: "",
        images = doc.get("images", Document::class.java)?.let { documentToImages(it) } ?: Images()
    )
}
