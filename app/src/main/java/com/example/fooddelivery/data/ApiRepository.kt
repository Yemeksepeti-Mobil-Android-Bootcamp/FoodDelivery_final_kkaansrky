package com.example.fooddelivery.data

import com.example.fooddelivery.data.entity.login.LoginRequest
import com.example.fooddelivery.data.entity.mealadd.MealAddRequest
import com.example.fooddelivery.data.entity.order.OrdersRequest
import com.example.fooddelivery.data.entity.register.RegisterRequest
import com.example.fooddelivery.data.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddelivery.data.local.LocalDataSource
import com.example.fooddelivery.data.remote.RemoteDataSource
import com.example.fooddelivery.utils.performAuthTokenNetworkOperation
import com.example.fooddelivery.utils.performNetworkOperation
import com.example.fooddelivery.utils.room.LocalOrder
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

    fun postOrders(request: OrdersRequest) =
        performNetworkOperation {
            remoteDataSource.postOrders(request)
        }

    fun postRestaurant(restaurantAddRequest: RestaurantAddRequest) =
        performNetworkOperation {
            remoteDataSource.postRestaurant(request = restaurantAddRequest)
        }

    fun postMeal(restaurantId: String, mealAddRequest: MealAddRequest) =
        performNetworkOperation {
            remoteDataSource.postMeal(restaurantId, request = mealAddRequest)
        }


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

    fun getRestaurantById(id: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantById(id)
        }

    fun getRestaurantByCuisine(cuisine: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantsByCuisine(cuisine)
        }

    fun getMealById(id: String) =
        performNetworkOperation {
            remoteDataSource.getMealById(id)
        }

    fun listOrders():List<LocalOrder> {
        return localDataSource.listLocalOrders()
    }

    fun addOrder(localOrder : LocalOrder){
        localDataSource.addLocalOrder(localOrder)
    }

    fun removeOrder(localOrder : LocalOrder){
        localDataSource.removeLocalOrder(localOrder)
    }

    fun getLocalOrderById(): LocalOrder {
        return localDataSource.getLocalOrderById()
    }
}