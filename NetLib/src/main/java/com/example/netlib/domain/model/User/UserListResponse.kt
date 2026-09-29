package com.example.netlib.domain.model.User

import com.example.netlib.domain.model.Position.PositionRecord
import kotlinx.serialization.Serializable

@Serializable
data class UserListResponse(
    val page: String,
    val perPage: String,
    val totalItems: String,
    val totalPages: String,
    val items: List<UserRecord>,
)
