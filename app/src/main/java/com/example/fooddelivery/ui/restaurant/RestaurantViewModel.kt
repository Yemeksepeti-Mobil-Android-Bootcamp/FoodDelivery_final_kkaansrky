package com.example.fooddelivery.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.ApiRepository
import com.example.fooddelivery.data.entity.meal.Meal
import com.example.fooddelivery.data.entity.restaurant.RestaurantResponse
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

    private fun checkRestaurantId(restaurantID: String): List<Meal> {
        val order = apiRepository.getLocalOrderById()
        if (order != null){
            if (restaurantID != order.restaurantID){
                return emptyList()
            }else{
                return getMealsInRoom()
            }
        }else {
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

    fun getTestItemMealsList(): ArrayList<Meal> {
        val mealsList = ArrayList<Meal>()

        mealsList.add(
            Meal(
                "1",
                "80 gr. %100 dana eti, turşu, özel sos, patates kızartması",
                "https://cdn.yemek.com/mncrop/940/625/uploads/2016/05/ev-yapimi-hamburger.jpg",
                ArrayList(),
                "Hamburger",
                "50,00 TL", 1
            )
        )
        mealsList.add(
            Meal(
                "2",
                "80 gr. %100 dana eti, turşu, özel sos, patates kızartması",
                "https://thehealthyfoodie.com/wp-content/uploads/2013/03/Tuna-and-Grilled-Zucchini-Salad-6.jpg",
                ArrayList(),
                "Salad",
                "25,00 TL", 1
            )
        )
        mealsList.add(
            Meal(
                "3",
                "Mozzarella peyniri, Kaşar peyniri, Sucuk, Sosis, Domates kurusu, Mantar, Mısır",
                "https://i.nefisyemektarifleri.com/2019/10/04/evde-pizza-tarifi-nasil-yapilir-12.jpg",
                ArrayList(),
                "Pizza",
                "70,00 TL",
                1
            )
        )

        return mealsList
    }
}
