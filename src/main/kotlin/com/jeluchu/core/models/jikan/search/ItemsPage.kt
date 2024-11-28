package com.jeluchu.core.models.jikan.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * ItemsPage data class.
 */
@Serializable
data class ItemsPage(
    /**
     * Count page.
     */
    @SerialName("count")
    val count: Int? = 0,

    /**
     * Total items availables.
     */
    @SerialName("total")
    val total: Int? = 0,

    /**
     * Total items per page.
     */
    @SerialName("per_page")
    val itemsPerPage: Int? = 0
)