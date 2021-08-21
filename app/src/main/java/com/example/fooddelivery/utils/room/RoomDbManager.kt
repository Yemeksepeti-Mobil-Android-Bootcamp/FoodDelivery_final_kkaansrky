package com.example.fooddelivery.utils.room

import android.content.Context
import androidx.room.Room

object RoomDbManager {
    fun initialize(context: Context) {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "LocalDb")
            .allowMainThreadQueries().build()
    }
}