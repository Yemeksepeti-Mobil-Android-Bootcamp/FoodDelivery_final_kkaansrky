package com.example.fooddelivery.ui.restaurant

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.entity.Category
import com.example.fooddelivery.data.entity.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor (
    var savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun getTestItemAddMealsList(): ArrayList<Meal> {
        val mealsList = ArrayList<Meal>()

        mealsList.add( Meal(
            "1",
            "80 gr. %100 dana eti, turşu, özel sos, patates kızartması",
            "https://cdn.yemek.com/mncrop/940/625/uploads/2016/05/ev-yapimi-hamburger.jpg",
            ArrayList(),
            "Hamburger",
            "50,00 TL"))
        mealsList.add( Meal(
            "2",
            "80 gr. %100 dana eti, turşu, özel sos, patates kızartması",
            "https://thehealthyfoodie.com/wp-content/uploads/2013/03/Tuna-and-Grilled-Zucchini-Salad-6.jpg",
            ArrayList(),
            "Salad",
            "25,00 TL"))
        mealsList.add( Meal(
            "3",
            "Mozzarella peyniri, Kaşar peyniri, Sucuk, Sosis, Domates kurusu, Mantar, Mısır",
            "https://i.nefisyemektarifleri.com/2019/10/04/evde-pizza-tarifi-nasil-yapilir-12.jpg",
            ArrayList(),
            "Pizza",
            "70,00 TL"))

        return mealsList
    }
}
