package com.base.auth

import com.base.model.Token
import com.base.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthManager {
    val currentUser: StateFlow<User?>
    val isLoggedIn: StateFlow<Boolean>

    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(email: String, password: String, name: String): Result<User>
    suspend fun logout()
    
    /**
     * Called when the app starts to check if a valid session exists.
     */
    suspend fun initialize()
}
