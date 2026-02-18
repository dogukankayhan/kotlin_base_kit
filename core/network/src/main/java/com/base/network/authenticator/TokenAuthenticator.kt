package com.base.network.authenticator

import com.base.domain.repository.TokenRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenRepository: TokenRepository
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            val currentToken = runBlocking {
                tokenRepository.getAccessToken().first()
            }
            
            val requestToken = response.request.header("Authorization")?.substringAfter("Bearer ")

            // If the token has changed since the request was made, retry with the new token
            if (currentToken != null && currentToken != requestToken) {
                return response.request.newBuilder()
                    .header("Authorization", "Bearer $currentToken")
                    .build()
            }

            // If the token hasn't changed, we need to refresh it
            return construnctNewRequest(response)
        }
    }

    private fun construnctNewRequest(response: Response): Request? {
        val refreshToken = runBlocking { 
            tokenRepository.getRefreshToken().first() 
        }
        
        if (refreshToken == null) return null 
        
        val newAccessToken = "NEW_ACCESS_TOKEN_FROM_REFRESH_${System.currentTimeMillis()}"

        runBlocking {
            tokenRepository.saveAccessToken(newAccessToken)
        }
        return response.request.newBuilder()
            .header("Authorization", "Bearer $newAccessToken")
            .build()
    }
}
