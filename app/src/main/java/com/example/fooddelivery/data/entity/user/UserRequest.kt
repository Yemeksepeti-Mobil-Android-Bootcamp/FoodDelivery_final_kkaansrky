package com.example.fooddelivery.data.entity.user

import com.google.gson.annotations.SerializedName

data class UserRequest (
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("place")
    val place: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("profile_image")
    val profileImage: String? = null,
)