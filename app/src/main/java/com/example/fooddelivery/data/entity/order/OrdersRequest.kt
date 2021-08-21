package com.example.fooddelivery.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrdersRequest(
    @SerializedName("restaurantId")
    val restaurantId: String,
    @SerializedName("mealIds")
    val mealIds: ArrayList<String>,
)