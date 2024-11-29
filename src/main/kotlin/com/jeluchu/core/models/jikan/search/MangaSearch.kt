package com.jeluchu.core.models.jikan.search

import com.jeluchu.core.models.jikan.manga.MangaData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaSearch(
    @SerialName("data")
    val data: List<MangaData>? = emptyList(),

    @SerialName("pagination")
    val pagination: Pagination? = Pagination()
)