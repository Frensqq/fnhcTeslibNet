package com.example.netlib.domain.model.Department

import kotlinx.serialization.Serializable

@Serializable
data class DepartmentListResponse(
    val page: String,
val perPage: String,
val totalItems: String,
val totalPages: String,
val items: List<DepartmentsRecord>,
)
