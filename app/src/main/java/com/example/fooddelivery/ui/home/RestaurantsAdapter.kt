package com.example.fooddelivery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.data.entity.restaurant.Restaurant
import com.example.fooddelivery.databinding.ItemHomeRestaurantBinding

class RestaurantsAdapter: RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {

    private var restaurantsList = ArrayList<Restaurant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding = ItemHomeRestaurantBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val restaurant = restaurantsList[position]

        holder.binding.restaurantTextView.text=restaurant.name

        holder.binding.itemCardView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRestaurantFragment(restaurant.id)
            it.findNavController().navigate(action)
        }

    }

    fun setRestaurantsList(restaurants: ArrayList<Restaurant>) {
        restaurantsList = restaurants
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = restaurantsList.size

    inner class RestaurantsViewHolder(val binding: ItemHomeRestaurantBinding): RecyclerView.ViewHolder(binding.root)
}