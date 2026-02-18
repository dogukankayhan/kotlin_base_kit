package com.base.network.model

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: ApiException) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}
