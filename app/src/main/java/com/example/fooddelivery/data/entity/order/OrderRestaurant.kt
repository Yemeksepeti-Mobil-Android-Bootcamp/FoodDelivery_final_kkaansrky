package com.example.fooddelivery.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderRestaurant (
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val image: String
)
