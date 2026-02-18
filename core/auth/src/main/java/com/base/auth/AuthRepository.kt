package com.base.auth

import com.base.model.Token
import com.base.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Token>
    suspend fun register(email: String, password: String, name: String): Result<Token>
    suspend fun getUserProfile(token: String): Result<User>
    suspend fun logout()
}
