package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Images data class.
 */
@Serializable
data class Images(
    /**
     * Images for jpg image type.
     */
    @SerialName("jpg")
    val jpg: ImageFormat? = null,

    /**
     * Images for webp image type.
     */
    @SerialName("webp")
    val webp: ImageFormat? = null
)