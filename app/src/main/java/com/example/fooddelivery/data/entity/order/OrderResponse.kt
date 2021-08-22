package com.example.fooddelivery.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("data")
    val orderList: ArrayList<Order>,
    @SerializedName("success")
    val success: Boolean
)
