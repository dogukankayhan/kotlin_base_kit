package com.base.data.repository

import com.base.auth.AuthRepository
import com.base.model.Token
import com.base.model.User
import com.base.model.request.LoginRequest
import com.base.model.request.RegisterRequest
import com.base.network.api.AuthApi
import com.base.network.model.NetworkResult
import com.base.network.utils.safeApiCall
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Token> {
        val networkResult = safeApiCall {
            authApi.login(LoginRequest(email, password))
        }
        return when (networkResult) {
            is NetworkResult.Success -> Result.success(networkResult.data)
            is NetworkResult.Error -> Result.failure(networkResult.exception)
            is NetworkResult.Loading -> Result.failure(Exception("Loading"))
        }
    }

    override suspend fun register(email: String, password: String, name: String): Result<Token> {
        val networkResult = safeApiCall {
            authApi.register(RegisterRequest(email, password, name))
        }
        return when (networkResult) {
            is NetworkResult.Success -> Result.success(networkResult.data)
            is NetworkResult.Error -> Result.failure(networkResult.exception)
            is NetworkResult.Loading -> Result.failure(Exception("Loading"))
        }
    }

    override suspend fun getUserProfile(token: String): Result<User> {
         val networkResult = safeApiCall {
             authApi.getUserProfile("Bearer $token")
         }
         return when (networkResult) {
            is NetworkResult.Success -> Result.success(networkResult.data)
            is NetworkResult.Error -> Result.failure(networkResult.exception)
            is NetworkResult.Loading -> Result.failure(Exception("Loading"))
        }
    }

    override suspend fun logout() {
        // Local logout logic (clearing tokens) is handled by AuthManager or TokenRepository
    }
}
