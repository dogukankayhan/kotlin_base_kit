package com.base.network.model

import java.io.IOException

/**
 * Base exception for all network-related errors.
 */
sealed class NetworkException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : IOException(message, cause) {

    /**
     * Represents a non-successful HTTP status code (e.g., 400, 404, 500).
     */
    class ApiException(
        val code: Int,
        message: String? = null,
        cause: Throwable? = null
    ) : NetworkException(message, cause) {
        override fun toString(): String = "ApiException(code=$code, message=$message)"
    }

    /**
     * Represents network connectivity issues (e.g., no internet, timeout).
     */
    class NoInternetException(
        message: String? = "No internet connection",
        cause: Throwable? = null
    ) : NetworkException(message, cause)

    /**
     * Represents unhandled exceptions during request execution.
     */
    class UnknownException(
        message: String? = "Unknown error occurred",
        cause: Throwable? = null
    ) : NetworkException(message, cause)
}
