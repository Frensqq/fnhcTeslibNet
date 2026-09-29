package com.example.netlib.domain.model.CandidateCards

import kotlinx.serialization.Serializable

@Serializable
data class CandidateCardsListResponse(
    val page: String,
    val perPage: String,
    val totalItems: String,
    val totalPages: String,
    val items: List<CandidateCardsRecord>,
)
