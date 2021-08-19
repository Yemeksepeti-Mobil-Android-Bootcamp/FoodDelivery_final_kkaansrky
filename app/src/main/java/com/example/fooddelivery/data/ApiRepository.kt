package com.example.fooddelivery.data

import com.example.fooddelivery.data.entity.login.LoginRequest
import com.example.fooddelivery.data.entity.register.RegisterRequest
import com.example.fooddelivery.data.local.LocalDataSource
import com.example.fooddelivery.data.remote.RemoteDataSource
import com.example.fooddelivery.utils.performAuthTokenNetworkOperation
import com.example.fooddelivery.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var localDataSource: LocalDataSource,
    private var remoteDataSource: RemoteDataSource
) {
    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )
    
    fun register(request: RegisterRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postRegister(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun logOutUser(){
        localDataSource.saveToken("")
    }

    fun getUser() =
        performNetworkOperation {
            remoteDataSource.getUser()
        }

    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
        }
}