package com.example.fooddelivery.data.entity.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("role")
    val role: String,
    @SerializedName("about")
    val about: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("place")
    val address: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("resetPasswordExpire")
    val resetPasswordExpire: String,
    @SerializedName("resetPasswordToken")
    val resetPasswordToken: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("paymentMethod")
    val paymentMethod: Int
)