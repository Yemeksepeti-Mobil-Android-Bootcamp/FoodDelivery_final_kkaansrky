package com.example.fooddelivery.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.ApiRepository
import com.example.fooddelivery.data.entity.meal.Meal
import com.example.fooddelivery.data.entity.restaurant.RestaurantResponse
import com.example.fooddelivery.data.entity.user.UserResponse
import com.example.fooddelivery.utils.Resource
import com.example.fooddelivery.utils.room.LocalOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getRestaurantDetail(id: String): LiveData<Resource<RestaurantResponse>> =
        apiRepository.getRestaurantById(id)

    fun addRestaurantIdInRoom(restaurantID: String) {
        val mealList = checkRestaurantId(restaurantID)
        apiRepository.addOrder(LocalOrder("1", restaurantID, mealList))
    }

    fun getUser(): LiveData<Resource<UserResponse>> {
        return apiRepository.getUser()
    }

    private fun checkRestaurantId(restaurantID: String): List<Meal> {
        val order = apiRepository.getLocalOrderById()
        if (order != null) {
            if (restaurantID != order.restaurantID) {
                return emptyList()
            } else {
                return getMealsInRoom()
            }
        } else {
            return emptyList()
        }
    }

    fun getMealsInRoom(): List<Meal> {
        val order = apiRepository.getLocalOrderById()
        if (order.meals.isEmpty()) {
            return emptyList()
        } else {
            return order.meals
        }
    }
}
