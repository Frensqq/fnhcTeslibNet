package com.example.netlib.domain.model.CandidateCardsComments

import kotlinx.serialization.Serializable

@Serializable
data class CandidateCardsCommentsListResponse(
    val page: String,
    val perPage: String,
    val totalItems: String,
    val totalPages: String,
    val items: List<CandidateCardsCommentsRecord>,
)
