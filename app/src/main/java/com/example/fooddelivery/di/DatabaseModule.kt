package com.example.fooddelivery.di

import android.content.Context
import androidx.room.Room
import com.example.fooddelivery.data.local.LocalDataSource
import com.example.fooddelivery.data.local.SharedPrefManager
import com.example.fooddelivery.utils.room.AppDatabase
import com.example.fooddelivery.utils.room.OrderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class
)
class DatabaseModule {

    @Provides
    fun sharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Provides
    fun localDataSource(sharedPrefManager: SharedPrefManager,orderDao: OrderDao): LocalDataSource {
        return LocalDataSource(sharedPrefManager,orderDao)
    }

    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "LocalDb")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): OrderDao {
        return appDatabase.orderDao()
    }
}