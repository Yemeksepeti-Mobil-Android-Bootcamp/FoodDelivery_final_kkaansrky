package com.example.fooddelivery.data.entity.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val user: User,
    @SerializedName("success")
    val success: Boolean
)