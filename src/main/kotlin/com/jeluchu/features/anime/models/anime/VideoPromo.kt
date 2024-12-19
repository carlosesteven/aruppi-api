package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
open class VideoPromo(
    /**
     * Embed url for trailer.
     */
    val embedUrl: String = "",

    /**
     * Url for trailer.
     */
    val url: String = "",

    /**
     * Youtube id for trailer.
     */
    val youtubeId: String = "",

    /**
     * Images for trailer.
     */
    val images: Images = Images()
)