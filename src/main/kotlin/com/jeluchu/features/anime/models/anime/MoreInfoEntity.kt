package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

@Serializable
data class MoreInfoEntity(
    var malId: Int = 0,
    val id: String = "",
    var title: String = "",
    var poster: String = "",
    var cover: String = "",
    var genres: List<String> = emptyList(),
    var synopsis: String = "",
    var episodes: List<MergedEpisode> = emptyList(),
    var episodesCount: Int = 0,
    var score: String = "",
    var staff: List<Staff> = emptyList(),
    var characters: List<Character> = emptyList(),
    var status: String = "",
    var type: String = "",
    val url: String = "",
    val promo: VideoPromo? = VideoPromo(),
    val duration: String = "",
    val rank: Int = 0,
    val titles: List<AlternativeTitles> = emptyList(),
    val airing: Boolean = false,
    val aired: AiringTime = AiringTime(),
    val broadcast: AnimeBroadcast = AnimeBroadcast(),
    val season: String = "",
    val year: Int = 0,
    val external: List<ExternalLinks> = emptyList(),
    val streaming: List<ExternalLinks> = emptyList(),
    val studios: List<Companies> = emptyList(),
    val licensors: List<Companies> = emptyList(),
    val producers: List<Companies> = emptyList(),
    val theme: Themes = Themes(),
    val relations: List<Related> = emptyList(),
    val stats: Statistics = Statistics(),
    val gallery: List<ImageMediaEntity> = emptyList(),
    val episodeSource: String = ""
) {
    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromDocument(document: Document): MoreInfoEntity = json.decodeFromString(document.toJson())
    }
}