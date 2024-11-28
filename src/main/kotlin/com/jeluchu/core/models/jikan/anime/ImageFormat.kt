package com.jeluchu.core.models.jikan.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageFormat(
    @SerialName("image_url")
    val generic: String? = "",

    @SerialName("small_image_url")
    val small: String? = "",

    @SerialName("medium_image_url")
    val medium: String? = "",

    @SerialName("large_image_url")
    val large: String? = "",

    @SerialName("maximum_image_url")
    val maximum: String? = ""
)