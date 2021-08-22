package com.example.fooddelivery.ui.lastorders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.order.OrderRestaurant
import com.example.fooddelivery.databinding.ItemLastOrdersBinding

class LastOrdersAdapter : RecyclerView.Adapter<LastOrdersAdapter.LastOrdersViewHolder>() {
    private var restaurants = ArrayList<OrderRestaurant>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastOrdersViewHolder {
        val binding = ItemLastOrdersBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LastOrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LastOrdersViewHolder, position: Int) {
        val restaurant = restaurants[position]

        holder.binding.apply {
            restaurantNameTextView.text = restaurant.name

            Glide
                .with(holder.itemView.context)
                .load(restaurant.image)
                .placeholder(R.drawable.ic_restaurant_temp_photo)
                .into(restaurantImageView)
        }

    }

    fun setRestaurants(restaurants: ArrayList<OrderRestaurant>) {
        this.restaurants = restaurants
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = restaurants.size

    inner class LastOrdersViewHolder(val binding: ItemLastOrdersBinding) :
        RecyclerView.ViewHolder(binding.root)
}