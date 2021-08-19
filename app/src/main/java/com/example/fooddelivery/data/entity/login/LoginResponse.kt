package com.example.fooddelivery.data.entity.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val loginData: LoginData,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)