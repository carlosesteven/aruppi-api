package com.jeluchu.core.models.jikan.character

import com.jeluchu.core.models.jikan.search.Pagination
import fordelete.CharacterData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterSearch(
    @SerialName("data")
    val data: List<CharacterData>? = emptyList(),

    @SerialName("pagination")
    val pagination: Pagination? = Pagination()
)