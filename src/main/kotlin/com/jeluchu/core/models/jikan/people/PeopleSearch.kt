package com.jeluchu.core.models.jikan.people

import com.jeluchu.core.models.jikan.search.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleSearch(
    @SerialName("data")
    val data: List<PeopleData>? = emptyList(),

    @SerialName("pagination")
    val pagination: Pagination? = Pagination()
)