package com.base.network.model

import java.io.IOException

sealed class ApiException(
    override val message: String? = null, 
    override val cause: Throwable? = null
) : IOException(message, cause) {
    class NetworkError(override val cause: Throwable? = null) : ApiException("Network Error", cause)
    class HttpError(val code: Int, override val message: String?, val body: String? = null) : ApiException(message)
    class TimeoutError(override val cause: Throwable? = null) : ApiException("Timeout Error", cause)
    class UnknownError(override val cause: Throwable? = null) : ApiException("Unknown Error", cause)
    class ParseError(override val cause: Throwable? = null) : ApiException("Parse Error", cause)
}
