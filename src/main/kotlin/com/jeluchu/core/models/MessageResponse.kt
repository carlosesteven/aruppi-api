package com.jeluchu.core.models

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    val message: String
)