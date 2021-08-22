package com.example.fooddelivery.data.remote

import com.example.fooddelivery.data.entity.login.LoginRequest
import com.example.fooddelivery.data.entity.mealadd.MealAddRequest
import com.example.fooddelivery.data.entity.order.OrdersRequest
import com.example.fooddelivery.data.entity.register.RegisterRequest
import com.example.fooddelivery.data.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddelivery.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): BaseDataSource(){
    suspend fun postLogin(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    suspend fun postRegister(request: RegisterRequest) = getResult {
        apiService.register(request)
    }

    suspend fun postOrders(request: OrdersRequest) = getResult {
        apiService.postOrders(request)
    }

    suspend fun postRestaurant(request: RestaurantAddRequest) = getResult {
        apiService.postRestaurant(request)
    }

    suspend fun postMeal(restaurantId: String, request: MealAddRequest) = getResult {
        apiService.postMeal(restaurantId, request)
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

    suspend fun getRestaurantsByCuisine(cuisine: String) = getResult {
        apiService.getRestaurantsByCuisine(cuisine)
    }

    suspend fun getMealById(id: String) = getResult {
        apiService.getMealById(id)
    }
}