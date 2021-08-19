package com.example.fooddelivery.data.remote

import com.example.fooddelivery.data.entity.login.LoginRequest
import com.example.fooddelivery.data.entity.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}