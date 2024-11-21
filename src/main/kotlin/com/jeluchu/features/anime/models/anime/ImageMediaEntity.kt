package com.example.models

import kotlinx.serialization.Serializable

@Serializable
    data class ImageMediaEntity(
        val media: String,
        val thumbnail: String,
        val width: Int,
        val height: Int,
        val url: String
    )