package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class MoreInfoEntity(
    val id: String? = null,
    var malId: Int? = null,
    val rank: Int? = null,
    var title: String = "",
    var episodes: Int? = null,
    var episodeList: List<EpisodeInfo>? = null,
    var type: String = "",
    var status: String = "",
    var season: SeasonalEntity? = null,
    var poster: String = "",
    var cover: String? = null,
    var duration: EpisodeDurationEntity? = null,
    var score: String? = null,
    var titles: OtherTitlesEntity? = null,
    val studios: List<Companies>? = null,
    val producers: List<Companies>? = null,
    val licensors: List<Companies>? = null,
    val relations: List<Related>? = null,
    val promo: VideoPromo? = null,
    var tags: MultipleLanguageLists? = null,
    var synopsis: MultipleLanguage? = null,
    var staff: List<Staff>? = null,
    var characters: List<Character>? = null,
    val streaming: List<ExternalLinks>? = null,
    val urls: List<String>? = null,
    val broadcast: AnimeBroadcast? = null,
    val external: List<ExternalLinks>? = null,
    val stats: Statistics? = null,
    val nsfw: Boolean = false,
    val ageRating: String? = null,
    val aired: AiringTime? = null,
    val themes: Themes? = null
)