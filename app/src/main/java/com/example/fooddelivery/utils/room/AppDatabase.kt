package com.example.fooddelivery.utils.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [LocalOrder::class], version = 2)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
}