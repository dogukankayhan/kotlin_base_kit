package com.base.network.interceptor

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Automatically retries failed server-side requests (5xx) with exponential backoff.
 */
@Singleton
class RetryInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val MAX_RETRIES = 3
        private const val INITIAL_BACKOFF_MS = 1000L // 1 second
        private const val SERVER_ERROR_START = 500
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        var tryCount = 0
        var backoff = INITIAL_BACKOFF_MS

        while (!response.isSuccessful && response.code >= SERVER_ERROR_START && tryCount < MAX_RETRIES) {
            tryCount++
            response.close()

            // Exponential backoff
            try {
                Thread.sleep(backoff)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                throw IOException(e)
            }
            backoff *= 2

            // Retry the request
            response = chain.proceed(request)
        }

        return response
    }
}
