package com.example.netlib.domain.model

import io.ktor.http.content.PartData

sealed class NetworkResult <out T>{
    object NoInternet: NetworkResult<Nothing>()
    data class ErrorResponse(val error: Error): NetworkResult<Nothing>()
    data class Success<T>(val data: T): NetworkResult<T>()

}