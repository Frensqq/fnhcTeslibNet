package com.example.netlib.domain.model.CandidateCards

import kotlinx.serialization.Serializable

@Serializable
data class CandidateCardsUpdate(
    val lastName: String,
    val firstName: String,
    val patronymic: String,
    val phone: String,
    val email: String,
    val city: String,
    val status: String,
    val vacancy: String,
    val resume: String,
    val avatar: String,
    val source: String,
    val comment: String,
)
