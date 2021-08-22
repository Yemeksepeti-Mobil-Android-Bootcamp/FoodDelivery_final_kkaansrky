package com.example.fooddelivery.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.ApiRepository
import com.example.fooddelivery.data.entity.restaurant.Restaurant
import com.example.fooddelivery.data.entity.restaurant.RestaurantListResponse
import com.example.fooddelivery.data.entity.user.UserResponse
import com.example.fooddelivery.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    var restaurantsList: List<Restaurant>? = null
    var categoriesList = arrayListOf<String>("All")

    fun getRestaurantsList(): LiveData<Resource<RestaurantListResponse>> {
        return apiRepository.getRestaurants()
    }

    fun getRestaurantByCuisine(cuisine: String): LiveData<Resource<RestaurantListResponse>> {
        return apiRepository.getRestaurantByCuisine(cuisine)
    }

    fun getAndSetCategoryListFromRestaurants(): ArrayList<String> {
        if (restaurantsList != null){
            for (restaurant in restaurantsList!!){
                if (categoriesList.find { category ->
                        category == restaurant.cuisine
                    } ==null){
                    categoriesList.add(restaurant.cuisine)
                }
            }
        }
        return categoriesList
    }

    fun searchRestaurantsGetFilteredList(text: String?): List<Restaurant>? {
        if (text.isNullOrEmpty()){
            return restaurantsList
        }

        val filteredRestaurantList: MutableList<Restaurant> = mutableListOf()
        restaurantsList?.forEach { restaurant ->
            if (restaurant.name.contains(text, true)){
                filteredRestaurantList.add(restaurant)
            }else if (restaurant.district.contains(text, true)){
                filteredRestaurantList.add(restaurant)
            }
        }
        return filteredRestaurantList
    }

    fun getUser(): LiveData<Resource<UserResponse>> {
        return apiRepository.getUser()
    }

    fun getTestItemCategoriesList(): ArrayList<String> {
        val categoriesList = ArrayList<String>()

        categoriesList.add("Hamburger")
        categoriesList.add("Salad")
        categoriesList.add("Pizza")

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
