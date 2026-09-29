package com.example.netlib.domain.model.CandidateCardsComments

import kotlinx.serialization.Serializable

@Serializable
data class CandidateCardsCommentsUpdate(
    val card: String,
    val text: String,
    val author: String,
)
