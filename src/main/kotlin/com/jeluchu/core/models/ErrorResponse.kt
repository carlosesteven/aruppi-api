package com.jeluchu.core.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: String
)