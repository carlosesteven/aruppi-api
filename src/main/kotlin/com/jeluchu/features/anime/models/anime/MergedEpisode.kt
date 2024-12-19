package com.jeluchu.features.anime.models.anime

import kotlinx.serialization.Serializable

@Serializable
data class MergedEpisode(
    var malId: Int,
    var title: String,
    var titleJapanese: String,
    var titleRomanji: String,
    var aired: String,
    var score: Float,
    var filler: Boolean,
    var recap: Boolean,
)

