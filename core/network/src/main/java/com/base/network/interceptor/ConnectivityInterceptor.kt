package com.base.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Checks for internet connectivity before executing a request.
 * Throws a NoConnectivityException if there is no active internet connection.
 */
@Singleton
class ConnectivityInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()) {
            throw NoConnectivityException("No internet connection")
        }
        return chain.proceed(chain.request())
    }

    @Suppress("ReturnCount")
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        val network = connectivityManager.activeNetwork ?: run {
            Log.d("ConnectivityInterceptor", "No active network")
            return false
        }

        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: run {
            Log.d("ConnectivityInterceptor", "No network capabilities")
            return false
        }

        val hasInternet = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        val isValidated = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        Log.d("ConnectivityInterceptor", "Has internet: $hasInternet, Is validated: $isValidated")
        
        return hasInternet && isValidated
    }
}


class NoConnectivityException(message: String) : IOException(message)
