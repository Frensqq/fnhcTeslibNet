package com.example.netlib.domain.model.CandidateStatuses

import kotlinx.serialization.Serializable

@Serializable
data class CandidateStatusesListResponse(
    val page: String,
    val perPage: String,
    val totalItems: String,
    val totalPages: String,
    val items: List<CandidateStatusesRecord>,
)
