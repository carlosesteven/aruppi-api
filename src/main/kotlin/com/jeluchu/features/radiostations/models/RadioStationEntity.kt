package com.jeluchu.features.radiostations.models

import kotlinx.serialization.Serializable

@Serializable
data class RadioStationEntity(
    val name: String = "",
    val image: String = "",
    val genre: String = "",
    val stream: String = "",
    val website: String = ""
)