package com.example.fooddelivery.data.remote

import com.example.fooddelivery.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): BaseDataSource(){
}