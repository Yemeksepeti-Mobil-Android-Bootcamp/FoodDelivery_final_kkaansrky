package com.example.fooddelivery.utils.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fooddelivery.data.entity.meal.Meal

@Entity
data class LocalOrder(
    @PrimaryKey var restaurantID: String,
    @ColumnInfo(name = "meals") var meals: List<Meal>
)