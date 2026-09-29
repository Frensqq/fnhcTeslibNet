package com.example.netlib.domain.model.Position

import kotlinx.serialization.Serializable

@Serializable
data class PositionListResponse(
    val page: String,
    val perPage: String,
    val totalItems: String,
    val totalPages: String,
    val items: List<PositionRecord>,
)
