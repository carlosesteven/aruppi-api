package com.jeluchu.core.models

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse(
    val success: String
)