package com.example.netlib.domain.model.ApplicantStatuses

import kotlinx.serialization.Serializable

@Serializable
data class ApplicantStatusesRecord(
    val collectionId: String,
val collectionName: String,
val id: String,
val name: String,
    val color: String,
    val sort: Int,
val created: String,
val updated: String,
)
