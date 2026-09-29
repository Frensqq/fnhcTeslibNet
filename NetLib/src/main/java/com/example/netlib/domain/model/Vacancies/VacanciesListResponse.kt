package com.example.netlib.domain.model.Vacancies

import kotlinx.serialization.Serializable

@Serializable
data class VacanciesListResponse(
    val page: String,
    val perPage: String,
    val totalItems: String,
    val totalPages: String,
    val items: List<VacanciesRecord>,
)
