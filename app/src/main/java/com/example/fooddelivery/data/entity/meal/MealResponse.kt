package com.example.fooddelivery.data.entity.meal

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("data")
    val data: Meal,
    @SerializedName("success")
    val success: Boolean
)