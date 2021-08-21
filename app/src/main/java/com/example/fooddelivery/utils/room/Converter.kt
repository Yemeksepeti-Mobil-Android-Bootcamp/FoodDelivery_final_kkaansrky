package com.example.fooddelivery.utils.room

import androidx.room.TypeConverter
import com.example.fooddelivery.data.entity.meal.Meal
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun listToJson(value: List<Meal>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Meal>::class.java).toList()
}