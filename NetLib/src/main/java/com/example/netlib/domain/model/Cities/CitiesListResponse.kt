package com.example.netlib.domain.model.Cities

import kotlinx.serialization.Serializable

@Serializable
data class CitiesListResponse(
    val page: String,
    val perPage: String,
    val totalItems: String,
    val totalPages: String,
    val items: List<CitiesRecord>,
)
