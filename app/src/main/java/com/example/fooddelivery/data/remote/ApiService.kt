package com.example.fooddelivery.data.remote

import com.example.fooddelivery.data.entity.login.LoginRequest
import com.example.fooddelivery.data.entity.login.LoginResponse
import com.example.fooddelivery.data.entity.register.RegisterRequest
import com.example.fooddelivery.data.entity.register.RegisterResponse
import com.example.fooddelivery.data.entity.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("auth/profile")
    suspend fun getUser() : Response<UserResponse>
}