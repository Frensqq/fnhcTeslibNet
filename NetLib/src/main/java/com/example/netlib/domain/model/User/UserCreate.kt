package com.example.netlib.domain.model.User

import kotlinx.serialization.Serializable

@Serializable
data class UserCreate(

    val email: String,
val avatar: String,
val firstName: String,
val lastName: String,
val patronymic: String,
val phone: String,
val department: String,
val position: String,
val role: String,

    val password: String,
val passwordConfirm: String,
)
