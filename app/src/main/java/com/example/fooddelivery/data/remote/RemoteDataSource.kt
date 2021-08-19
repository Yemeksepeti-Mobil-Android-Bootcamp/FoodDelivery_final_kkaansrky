package com.example.fooddelivery.data.remote

import com.example.fooddelivery.data.entity.login.LoginRequest
import com.example.fooddelivery.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): BaseDataSource(){
    suspend fun postLogin(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    suspend fun getUser() = getResult {
        apiService.getUser()
    }
}