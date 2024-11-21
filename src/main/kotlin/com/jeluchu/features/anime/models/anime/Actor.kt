package com.example.models

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