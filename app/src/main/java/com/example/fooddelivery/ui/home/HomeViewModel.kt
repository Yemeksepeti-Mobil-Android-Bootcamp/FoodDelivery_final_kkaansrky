package com.example.fooddelivery.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.entity.Categorie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun getTestItemAddCategoriesList(): ArrayList<Categorie> {
        val categoriesList = ArrayList<Categorie>()

        categoriesList.add(0, Categorie(1, "Hamburger"))
        categoriesList.add(1, Categorie(2, "Salad"))
        categoriesList.add(2, Categorie(3, "Pizza"))

        return categoriesList
    }

    fun getTestItemAddRestaurantList(): ArrayList<String> {
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
