package com.jeluchu.features.anime.mappers

import com.jeluchu.core.extensions.*
import com.jeluchu.core.models.animeflv.lastepisodes.EpisodeEntity
import com.jeluchu.features.anime.models.anime.*
import com.jeluchu.features.anime.models.directory.AnimeDirectoryEntity
import com.jeluchu.features.anime.models.directory.AnimeTypeEntity
import com.jeluchu.features.rankings.models.AnimeTopEntity
import com.jeluchu.features.rankings.models.CharacterTopEntity
import com.jeluchu.features.rankings.models.MangaTopEntity
import com.jeluchu.features.rankings.models.PeopleTopEntity
import com.jeluchu.features.schedule.models.DayEntity
import org.bson.Document
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun documentToMoreInfoEntity(doc: Document): MoreInfoEntity {
    return MoreInfoEntity(
        id = doc.getObjectId("_id").toString(),
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
        malId = doc.getIntSafe("malId"),
        title = doc.getStringSafe("title"),
        titleJapanese = doc.getStringSafe("titleJapanese"),
        titleRomanji = doc.getStringSafe("titleRomanji"),
        aired = doc.getStringSafe("aired", ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)),
        score = doc.getFloatSafe("score"),
        filler = doc.getBooleanSafe("filler"),
        recap = doc.getBooleanSafe("recap")
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

fun documentToAnimeTopEntity(doc: Document) = AnimeTopEntity(
    malId = doc.getIntSafe("malId"),
    rank = doc.getIntSafe("rank"),
    score = doc.getFloatSafe("score"),
    title = doc.getStringSafe("title"),
    image = doc.getStringSafe("image"),
    url = doc.getStringSafe("url"),
    promo = doc.getDocumentSafe("promo")?.let { documentToVideoPromo(it) } ?: VideoPromo(),
    season = doc.getStringSafe("season"),
    year = doc.getIntSafe("year"),
    airing = doc.getBooleanSafe("airing"),
    type = doc.getStringSafe("type"),
    subtype = doc.getStringSafe("subtype"),
    page = doc.getIntSafe("page"),
)

fun documentToMangaTopEntity(doc: Document) = MangaTopEntity(
    malId = doc.getIntSafe("malId"),
    rank = doc.getIntSafe("rank"),
    score = doc.getDoubleSafe("score"),
    title = doc.getStringSafe("title"),
    image = doc.getStringSafe("image"),
    url = doc.getStringSafe("url"),
    volumes = doc.getIntSafe("volumes"),
    chapters = doc.getIntSafe("chapters"),
    status = doc.getStringSafe("status"),
    type = doc.getStringSafe("type"),
    subtype = doc.getStringSafe("subtype"),
    page = doc.getIntSafe("page"),
)

fun documentToPeopleTopEntity(doc: Document) = PeopleTopEntity(
    malId = doc.getIntSafe("malId"),
    name = doc.getStringSafe("name"),
    givenName = doc.getStringSafe("givenName"),
    familyName = doc.getStringSafe("familyName"),
    image = doc.getStringSafe("image"),
    birthday = doc.getStringSafe("birthday"),
    page = doc.getIntSafe("page"),
    top = doc.getStringSafe("top"),
)

fun documentToCharacterTopEntity(doc: Document) = CharacterTopEntity(
    malId = doc.getIntSafe("malId"),
    name = doc.getStringSafe("name"),
    nameKanji = doc.getStringSafe("nameKanji"),
    image = doc.getStringSafe("image"),
    top = doc.getStringSafe("top"),
    page = doc.getIntSafe("page"),
)

fun documentToAnimeTypeEntity(doc: Document) = AnimeTypeEntity(
    score = doc.getString("score"),
    malId = doc.getIntSafe("malId"),
    year = doc.getIntSafe("year"),
    season = doc.getStringSafe("season"),
    type = doc.getStringSafe("type"),
    title = doc.getStringSafe("title"),
    image = doc.getStringSafe("poster"),
    episodes = doc.getListSafe<Document>("episodes").size
)

fun documentToAnimeDirectoryEntity(doc: Document) = AnimeTypeEntity(
    score = doc.getString("score"),
    malId = doc.getIntSafe("malId"),
    year = doc.getIntSafe("year"),
    season = doc.getStringSafe("season"),
    type = doc.getStringSafe("type"),
    title = doc.getStringSafe("title"),
    image = doc.getStringSafe("image"),
    episodes = doc.getListSafe<Document>("episodes").size
)

fun documentToLastEpisodesEntity(doc: Document) = EpisodeEntity(
    number = doc.getIntSafe("number"),
    title = doc.getStringSafe("title"),
    image = doc.getStringSafe("image")
)