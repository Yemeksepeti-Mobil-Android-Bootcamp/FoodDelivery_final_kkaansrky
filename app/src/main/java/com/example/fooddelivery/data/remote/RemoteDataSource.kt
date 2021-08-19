package com.example.fooddelivery.data.remote

import com.example.fooddelivery.data.entity.login.LoginRequest
import com.example.fooddelivery.data.entity.register.RegisterRequest
import com.example.fooddelivery.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): BaseDataSource(){
    suspend fun postLogin(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    suspend fun postRegister(request: RegisterRequest) = getResult {
        apiService.register(request)
    }

    suspend fun getUser() = getResult {
        apiService.getUser()
    }

    suspend fun getRestaurants() = getResult {
        apiService.getRestaurants()
    }

    suspend fun getRestaurantById(id: String) = getResult {
        apiService.getRestaurantById(id)
    }

}