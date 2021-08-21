package com.example.fooddelivery.data.entity.meal

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("_id")
    val id: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageUrl")
    val image: String,
    @SerializedName("ingredients")
    var ingredients: ArrayList<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    var quantity: Int
)