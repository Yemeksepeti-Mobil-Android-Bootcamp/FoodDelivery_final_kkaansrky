package com.example.fooddelivery.ui.addmeal

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.ApiRepository
import com.example.fooddelivery.data.entity.SuccessResponse
import com.example.fooddelivery.data.entity.mealadd.MealAddRequest
import com.example.fooddelivery.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMealViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun postMeal(
        restaurantId: String,
        mealAddRequest: MealAddRequest
    ): LiveData<Resource<SuccessResponse>> {
        return apiRepository.postMeal(restaurantId, mealAddRequest)
    }
}