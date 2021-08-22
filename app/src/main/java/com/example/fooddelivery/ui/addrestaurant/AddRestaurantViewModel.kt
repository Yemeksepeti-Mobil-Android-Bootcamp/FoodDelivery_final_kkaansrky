package com.example.fooddelivery.ui.addrestaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.ApiRepository
import com.example.fooddelivery.data.entity.SuccessResponse
import com.example.fooddelivery.data.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddelivery.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRestaurantViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {
    fun postRestuarant(restaurantAddRequest: RestaurantAddRequest): LiveData<Resource<SuccessResponse>> {
       return apiRepository.postRestaurant(restaurantAddRequest)
    }
}