package com.jeluchu.core.models.jikan.manga

import com.jeluchu.core.models.jikan.anime.*
import com.jeluchu.features.rankings.models.MangaTopEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaData(
    @SerialName("approved")
    val approved: Boolean? = false,

    @SerialName("authors")
    val authors: List<Demographic>? = emptyList(),

    @SerialName("background")
    val background: String? = "",

    @SerialName("chapters")
    val chapters: Int? = 0,

    @SerialName("demographics")
    val demographics: List<Demographic>? = emptyList(),

    @SerialName("explicit_genres")
    val explicitGenres: List<Genre>? = emptyList(),

    @SerialName("favorites")
    val favorites: Int? = 0,

    @SerialName("genres")
    val genres: List<Genre>? = emptyList(),

    @SerialName("images")
    val images: Images? = Images(),

    @SerialName("mal_id")
    val malId: Int? = 0,

    @SerialName("members")
    val members: Int? = 0,

    @SerialName("popularity")
    val popularity: Int? = 0,

    @SerialName("published")
    val published: Published? = Published(),

    @SerialName("publishing")
    val publishing: Boolean? = false,

    @SerialName("rank")
    val rank: Int? = 0,

    @SerialName("score")
    val score: Double? = 0.0,

    @SerialName("scored")
    val scored: Double? = 0.0,

    @SerialName("scored_by")
    val scoredBy: Int? = 0,

    @SerialName("serializations")
    val serializations: List<Demographic>? = emptyList(),

    @SerialName("status")
    val status: String? = "",

    @SerialName("synopsis")
    val synopsis: String? = "",

    @SerialName("themes")
    val themes: List<Themes>? = emptyList(),

    @Deprecated("Use 'titles: List<Title>' to get the title")
    @SerialName("title")
    val title: String? = "",

    @Deprecated("Use 'titles: List<Title>' to get the title")
    @SerialName("title_english")
    val titleEnglish: String? = "",

    @Deprecated("Use 'titles: List<Title>' to get the title")
    @SerialName("title_japanese")
    val titleJapanese: String? = "",

    @Deprecated("Use 'titles: List<Title>' to get the title")
    @SerialName("title_synonyms")
    val titleSynonyms: List<String>? = emptyList(),

    @SerialName("titles")
    val titles: List<Title>? = emptyList(),

    @SerialName("type")
    val type: String? = "",

    @SerialName("url")
    val url: String? = "",

    @SerialName("volumes")
    val volumes: Int? = 0
) {
    companion object {
        fun MangaData.toMangaTopEntity(
            page: Int,
            top: String,
            type: String,
            subType: String,
        ) = MangaTopEntity(
            malId = malId,
            rank = rank,
            score = score,
            image = images?.webp?.large.orEmpty(),
            title = titles?.first()?.title.orEmpty(),
            url = url,
            status = status,
            volumes = volumes,
            chapters = chapters,
            top = top,
            type = type,
            subtype = subType,
            page = page
        )
    }
}