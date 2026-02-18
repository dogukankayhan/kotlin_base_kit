package com.base.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {

    /**
     * Retrieves the access token as a Flow.
     */
    fun getAccessToken(): Flow<String?>

    /**
     * Retrieves the refresh token as a Flow.
     */
    fun getRefreshToken(): Flow<String?>

    /**
     * Saves the access token.
     */
    suspend fun saveAccessToken(token: String)

    /**
     * Saves the refresh token.
     */
    suspend fun saveRefreshToken(token: String)

    /**
     * Clears all tokens.
     */
    suspend fun clearTokens()
}
