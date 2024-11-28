package com.jeluchu.core.utils

import com.example.models.VideoPromo
import com.jeluchu.core.models.jikan.anime.ImageFormat
import com.jeluchu.core.models.jikan.anime.Trailer
import com.jeluchu.features.anime.models.anime.Images

fun Trailer.toVideoPromo() = VideoPromo(
    url = url.orEmpty(),
    youtubeId = youtubeId.orEmpty(),
    embedUrl = embedUrl.orEmpty(),
    images = images?.toImages() ?: Images()
)

fun ImageFormat.toImages() = Images(
    generic = generic.orEmpty(),
    small = small.orEmpty(),
    medium = medium.orEmpty(),
    large = large.orEmpty(),
    maximum = maximum.orEmpty()
)