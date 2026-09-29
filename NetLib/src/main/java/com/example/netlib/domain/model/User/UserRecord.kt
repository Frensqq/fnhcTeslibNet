package com.example.netlib.domain.model.User

import kotlinx.serialization.Serializable

@Serializable
data class UserRecord(
    val collectionId: String,
val collectionName: String,
val id: String,
    val email: String,
val emailVisibility: Boolean,
val verified: Boolean,
val avatar: String,
val firstName: String,
val lastName: String,
val patronymic: String,
val phone: String,
val department: String,
val position: String,
val role: String,
val created: String,
val updated: String,
)
