package com.example.fooddelivery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.data.entity.Restaurant
import com.example.fooddelivery.databinding.ItemHomeCategoriesBinding
import com.example.fooddelivery.databinding.ItemHomeRestaurantsBinding

class RestaurantsAdapter: RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {

    private var restaurantsList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding = ItemHomeRestaurantsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val restaurant = restaurantsList[position]

        holder.binding.restaurantTextView.text=restaurant

    }

    fun setQuizList(restaurants: ArrayList<String>) {
        restaurantsList = restaurants
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = restaurantsList.size

    inner class RestaurantsViewHolder(val binding: ItemHomeRestaurantsBinding): RecyclerView.ViewHolder(binding.root)
}