package com.jeluchu.core.models

import kotlinx.serialization.Serializable

@Serializable
data class PaginationResponse<T>(
    val page: Int = 0,
    val size: Int = 0,
    val totalPages: Int = 0,
    val totalItems: Int = 0,
    val data: List<T> = emptyList()
)
