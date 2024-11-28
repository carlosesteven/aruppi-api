package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Trailer data class.
 */
@Serializable
data class Trailer(
    /**
     * Embed url for trailer.
     */
    @SerialName("embed_url")
    val embedUrl: String? = "",

    /**
     * Url for trailer.
     */
    @SerialName("url")
    val url: String? = "",

    /**
     * Youtube id for trailer.
     */
    @SerialName("youtube_id")
    val youtubeId: String? = "",

    /**
     * Images for trailer.
     */
    @SerialName("images")
    val images: ImageFormat? = ImageFormat()
)