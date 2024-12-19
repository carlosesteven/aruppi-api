package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class Actor(
    /**
     * Person generic info.
     * @see Individual
     */
    val person: Individual = Individual(),

    /**
     * Language of the person.
     */
    val language: String = ""
)