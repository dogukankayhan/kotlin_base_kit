package com.base.network.api

import com.base.model.Token
import com.base.model.User
import com.base.network.model.NetworkResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

import com.base.model.request.LoginRequest
import com.base.model.request.RegisterRequest

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Token

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Token


    @GET("auth/me")
    suspend fun getUserProfile(@Header("Authorization") token: String): User
}
