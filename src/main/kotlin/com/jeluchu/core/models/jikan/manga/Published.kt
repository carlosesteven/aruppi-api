package com.jeluchu.core.models.jikan.manga

import com.jeluchu.core.models.jikan.anime.Prop
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Published(
    @SerialName("from")
    val from: String? = "",

    @SerialName("prop")
    val prop: Prop? = Prop(),

    @SerialName("string")
    val string: String? = "",

    @SerialName("to")
    val to: String? = ""
)