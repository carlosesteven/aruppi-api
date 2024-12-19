package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
    data class ImageMediaEntity(
        val media: String,
        val thumbnail: String,
        val width: Int,
        val height: Int,
        val url: String
    )