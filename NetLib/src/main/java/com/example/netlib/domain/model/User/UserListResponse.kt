package com.example.netlib.domain.model.User

import com.example.netlib.domain.model.Position.PositionRecord
import kotlinx.serialization.Serializable

@Serializable
data class UserListResponse(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<UserRecord>,
)
