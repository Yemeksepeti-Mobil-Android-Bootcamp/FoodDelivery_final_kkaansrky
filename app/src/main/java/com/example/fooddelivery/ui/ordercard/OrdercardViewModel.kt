package com.example.fooddelivery.ui.ordercard

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.ApiRepository
import com.example.fooddelivery.data.entity.SuccessResponse
import com.example.fooddelivery.data.entity.meal.Meal
import com.example.fooddelivery.data.entity.order.OrdersRequest
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

    fun setOrderInRoomDb(localOrder: LocalOrder) {
        apiRepository.addOrder(localOrder)
    }

    fun removeOrderInRoomDb(localOrderId : String) {
        apiRepository.removeOrder(localOrderId)
    }

    fun getRestaurantByID(restaurantID: String): LiveData<Resource<RestaurantResponse>> {
        return apiRepository.getRestaurantById(restaurantID)
    }

    fun getLocalOrderById(): LocalOrder {
        return apiRepository.getLocalOrderById()
    }

    fun setOrdersRequestObjectAndPost(
        restaurantID: String,
        meals: List<Meal>
    ): LiveData<Resource<SuccessResponse>> {
        val mealIdList = getMealIdList(meals)
        return apiRepository.postOrders(OrdersRequest(restaurantID, mealIdList))
    }

    private fun getMealIdList(meals: List<Meal>): ArrayList<String> {
        val mealIdList = ArrayList<String>()

        for (meal in meals) {
            mealIdList.add(meal.id)
        }

        return mealIdList
    }
}