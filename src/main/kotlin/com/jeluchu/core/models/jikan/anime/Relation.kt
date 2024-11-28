package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Relation data class.
 */
@Serializable
data class Relation(
    /**
     * List of entries for relation in anime.
     * @see Entry for the detail.
     */
    @SerialName("entry")
    val entry: List<Entry>?,

    /**
     * Relation for anime
     */
    @SerialName("relation")
    val relation: String?
)