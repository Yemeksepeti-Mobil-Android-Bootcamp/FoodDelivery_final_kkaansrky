package com.example.fooddelivery.ui.ordercard

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.ApiRepository
import com.example.fooddelivery.data.entity.meal.Meal
import com.example.fooddelivery.data.entity.restaurant.Restaurant
import com.example.fooddelivery.data.entity.restaurant.RestaurantResponse
import com.example.fooddelivery.utils.Resource
import com.example.fooddelivery.utils.room.LocalOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrdercardViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getOrderFromRoomDb(): List<LocalOrder> {
        return apiRepository.listOrders()
    }

    fun setOrderInRoomDb(localOrder: LocalOrder){
        apiRepository.addOrder(localOrder)
    }

    fun getRestaurantByID(restaurantID: String): LiveData<Resource<RestaurantResponse>> {
        return apiRepository.getRestaurantById(restaurantID)
    }
}