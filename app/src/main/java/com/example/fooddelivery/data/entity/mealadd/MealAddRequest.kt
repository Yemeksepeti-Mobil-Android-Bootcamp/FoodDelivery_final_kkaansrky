package com.example.fooddelivery.data.entity.mealadd

import com.google.gson.annotations.SerializedName

data class MealAddRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("ingredients")
    val ingredients: List<String>
)