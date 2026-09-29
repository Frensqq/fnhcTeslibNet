package com.example.netlib.domain.model.User

data class UserAuthResponse(
    val token: String,
    val record: UserRecord,
)
