package com.example.fooddelivery.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.ApiRepository
import com.example.fooddelivery.data.entity.Category
import com.example.fooddelivery.data.entity.restaurant.RestaurantListResponse
import com.example.fooddelivery.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getRestaurantsList(): LiveData<Resource<RestaurantListResponse>> {
        return apiRepository.getRestaurants()
    }

    fun getTestItemCategoriesList(): ArrayList<Category> {
        val categoriesList = ArrayList<Category>()

        categoriesList.add(Category(1, "Hamburger"))
        categoriesList.add(Category(2, "Salad"))
        categoriesList.add(Category(3, "Pizza"))

        return categoriesList
    }

    fun getTestItemRestaurantList(): ArrayList<String> {
        val restaurantList = ArrayList<String>()

        restaurantList.add( "Ayten Usta")
        restaurantList.add( "Burger King")
        restaurantList.add( "Pino")
        restaurantList.add( "Tok-Yat")
        restaurantList.add( "Elmacıoğlu")
        restaurantList.add( "Kasaba")

        return restaurantList
    }
}
