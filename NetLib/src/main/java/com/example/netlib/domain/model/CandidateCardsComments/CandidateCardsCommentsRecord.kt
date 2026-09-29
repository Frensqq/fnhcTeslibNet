package com.example.netlib.domain.model.CandidateCardsComments

import kotlinx.serialization.Serializable

@Serializable
data class CandidateCardsCommentsRecord(
    val collectionId: String,
val collectionName: String,
val id: String,
    val card: String,
val text: String,
val author: String,

val created: String,
val updated: String,
)
