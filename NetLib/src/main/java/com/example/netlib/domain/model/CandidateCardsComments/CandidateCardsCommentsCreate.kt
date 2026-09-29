package com.example.netlib.domain.model.CandidateCardsComments

import kotlinx.serialization.Serializable

@Serializable
data class CandidateCardsCommentsCreate(

    val card: String,
    val text: String,
    val author: String,
)
