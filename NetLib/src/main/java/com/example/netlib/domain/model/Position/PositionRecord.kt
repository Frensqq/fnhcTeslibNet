package com.example.netlib.domain.model.Position

import kotlinx.serialization.Serializable

@Serializable
data class PositionRecord(
    val collectionId: String,
val collectionName: String,
val id: String,
val name: String,
    val department: String,
val created: String,
val updated: String,
)
