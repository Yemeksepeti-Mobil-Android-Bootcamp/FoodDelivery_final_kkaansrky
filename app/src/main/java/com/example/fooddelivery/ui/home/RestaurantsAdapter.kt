package com.example.fooddelivery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.data.entity.Restaurant
import com.example.fooddelivery.databinding.ItemHomeCategoriesBinding
import com.example.fooddelivery.databinding.ItemHomeRestaurantsBinding

class RestaurantsAdapter: RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {

    private var restaurantsList = ArrayList<Restaurant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding = ItemHomeRestaurantBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val restaurant = restaurantsList[position]

        holder.binding.apply {
            restaurantTextView.text=restaurant.name
            Glide
                .with(holder.itemView.context)
                .load(restaurant.image)
                .placeholder(R.drawable.temp_meal)
                .into(restaurantImageView)
        }

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