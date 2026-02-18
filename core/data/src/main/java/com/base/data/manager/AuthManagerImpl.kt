package com.base.data.manager

import com.base.auth.AuthManager
import com.base.auth.AuthRepository
import com.base.domain.repository.TokenRepository
import com.base.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManagerImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
) : AuthManager {

    // private val scope = CoroutineScope(Dispatchers.IO) // Removed unused property

    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    override val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    override suspend fun initialize() {
        // Here we could check if we have a valid token, and if so, fetch the user profile
        // For simplicity, we just check if we have an access token
        tokenRepository.getAccessToken().collect { token ->
             if (!token.isNullOrBlank()) {
                 val result = authRepository.getUserProfile(token)
                 if (result.isSuccess) {
                     _currentUser.value = result.getOrNull()
                     _isLoggedIn.value = true
                 } else {
                     // Token might be invalid
                     logout()
                 }
             } else {
                 _isLoggedIn.value = false
                 _currentUser.value = null
             }
        }
    }

    override suspend fun login(email: String, password: String): Result<User> {
        val tokenResult = authRepository.login(email, password)
        if (tokenResult.isFailure) {
            return Result.failure(tokenResult.exceptionOrNull() ?: Exception("Login failed"))
        }

        val token = tokenResult.getOrThrow()
        tokenRepository.saveAccessToken(token.accessToken)
        tokenRepository.saveRefreshToken(token.refreshToken)
        
        // Fetch user profile after login
        val userResult = authRepository.getUserProfile(token.accessToken)
        return if (userResult.isSuccess) {
            val user = userResult.getOrThrow()
            _currentUser.value = user
            _isLoggedIn.value = true
            Result.success(user)
        } else {
            Result.failure(userResult.exceptionOrNull() ?: Exception("Failed to fetch profile"))
        }
    }

    override suspend fun register(email: String, password: String, name: String): Result<User> {
         val tokenResult = authRepository.register(email, password, name)
        if (tokenResult.isFailure) {
            return Result.failure(tokenResult.exceptionOrNull() ?: Exception("Register failed"))
        }

        val token = tokenResult.getOrThrow()
        tokenRepository.saveAccessToken(token.accessToken)
        tokenRepository.saveRefreshToken(token.refreshToken)
        
        // Fetch user profile after register
        val userResult = authRepository.getUserProfile(token.accessToken)
        return if (userResult.isSuccess) {
            val user = userResult.getOrThrow()
            _currentUser.value = user
            _isLoggedIn.value = true
            Result.success(user)
        } else {
            Result.failure(userResult.exceptionOrNull() ?: Exception("Failed to fetch profile"))
        }
    }

    override suspend fun logout() {
        tokenRepository.clearTokens()
        _currentUser.value = null
        _isLoggedIn.value = false
    }
}
