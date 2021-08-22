package com.example.fooddelivery.data.entity.order

import com.example.fooddelivery.data.entity.meal.Meal
import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("createdDate")
    val createdDate: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("meals")
    val meals: List<Meal>,
    @SerializedName("restaurant")
    val restaurant: OrderRestaurant,
    @SerializedName("user")
    val user: String
)
