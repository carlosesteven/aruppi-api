package com.jeluchu.core.models.jikan.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Pagination data class.
 */
@Serializable
data class Pagination(
    /**
     * Current page available.
     */
    @SerialName("current_page")
    val currentPage: Int? = 0,

    /**
     * Last page available.
     */
    @SerialName("last_visible_page")
    val lastPage: Int? = 0,

    /**
     * Items information.
     */
    @SerialName("items")
    val itemsPage: ItemsPage? = ItemsPage(),

    /**
     * Request hast next page or not.
     */
    @SerialName("has_next_page")
    val hasNextPage: Boolean? = null
)