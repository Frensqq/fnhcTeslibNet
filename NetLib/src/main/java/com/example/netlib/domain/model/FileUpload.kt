package com.example.netlib.domain.model

data class FileUpload(
    val name: String,
    val byte: ByteArray,
    val mimeType: String
)
