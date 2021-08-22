package com.example.fooddelivery.data.entity.restaurantadd

import com.google.gson.annotations.SerializedName

data class RestaurantAddRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("cuisine")
    val cuisine: String,
    @SerializedName("deliveryInfo")
    val deliveryInfo: String,
    @SerializedName("deliveryTime")
    val deliveryTime: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("minDeliveryFee")
    val minDeliveryFee: String,
    @SerializedName("paymentMethods")
    val paymentMethods: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("imageUrl")
    val imageUrl: String
)