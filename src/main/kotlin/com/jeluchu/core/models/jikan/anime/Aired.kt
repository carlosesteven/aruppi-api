package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Aired data class.
 */
@Serializable
data class Aired(
    /**
     * Start date in ISO8601 format.
     */
    @SerialName("from")
    val from: String? = "",

    /**
     * @see Prop for the detail.
     */
    @SerialName("prop")
    val prop: Prop? = Prop(),

    /**
     * End date in ISO8601 format.
     */
    @SerialName("to")
    val to: String? = ""
)