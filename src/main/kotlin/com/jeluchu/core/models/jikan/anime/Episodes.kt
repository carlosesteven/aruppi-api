package com.jeluchu.core.models.jikan.anime

import com.jeluchu.core.models.jikan.search.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Episodes(

    @SerialName("mal_id")
    val malId: Int? = 0,

    @SerialName("title")
    val title: String? = "",

    @SerialName("episode")
    val episode: String? = "",

    @SerialName("url")
    val url: String? = "",

    /**
     * Data list of all anime found.
     */
    @SerialName("data")
    val data: List<AnimeData>? = emptyList()
)