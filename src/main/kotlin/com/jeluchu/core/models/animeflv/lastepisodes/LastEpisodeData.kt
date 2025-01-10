package com.jeluchu.core.models.animeflv.lastepisodes

import kotlinx.serialization.Serializable

@Serializable
data class LastEpisodeData(
    val cover: String?,
    val number: Int?,
    val title: String?,
    val url: String?
) {
    companion object {
        fun LastEpisodeData.toEpisodeEntity() = EpisodeEntity(
            number = number ?: 0,
            image = cover.orEmpty(),
            title = title.orEmpty()
        )
    }
}