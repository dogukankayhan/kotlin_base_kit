package com.base.network.utils

import com.base.network.model.ApiException
import com.base.network.model.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): NetworkResult<T> {
    return withContext(dispatcher) {
        try {
            NetworkResult.Success(apiCall())
        } catch (@Suppress("TooGenericExceptionCaught") e: Throwable) {
            NetworkResult.Error(e.toApiException())
        }
    }
}

fun Throwable.toApiException(): ApiException {
    return when (this) {
        is ApiException -> this
        is SocketTimeoutException -> ApiException.TimeoutError(this)
        is UnknownHostException -> ApiException.NetworkError(this)
        is IOException -> ApiException.NetworkError(this)
        is HttpException -> {
            val errorBody = response()?.errorBody()?.string()
            ApiException.HttpError(code(), message(), errorBody)
        }
        else -> ApiException.UnknownError(this)
    }
}
