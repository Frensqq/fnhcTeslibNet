package com.example.netlib.domain.model.Applicants

import kotlinx.serialization.Serializable

@Serializable
data class ApplicantsListResponse(
    val page: String,
    val perPage: String,
    val totalItems: String,
    val totalPages: String,
    val items: List<ApplicantsRecord>,
)
