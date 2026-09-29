package com.example.netlib.domain.model.ApplicantStatuses

import kotlinx.serialization.Serializable

@Serializable
data class ApplicantStatusesListResponse(
    val page: String,
    val perPage: String,
    val totalItems: String,
    val totalPages: String,
    val items: List<ApplicantStatusesRecord>,
)
