package com.example.fooddelivery.ui.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.Meal
import com.example.fooddelivery.databinding.ItemRestaurantMealBinding
import com.example.fooddelivery.ui.home.HomeFragmentDirections

class MealsAdapter: RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {
    private var mealsList = ArrayList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val binding = ItemRestaurantMealBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        val meal = mealsList[position]

        holder.binding.apply {
            mealNameTextView.text=meal.name
            mealDescriptionTextView.text = meal.description
            val price = meal.price +" TL"
            mealPriceTextView.text = price


            Glide
                .with(holder.itemView.context)
                .load(meal.image)
                .placeholder(R.drawable.temp_meal)
                .into(mealImageView)

            itemCardView.setOnClickListener {
                val action = RestaurantFragmentDirections.actionRestaurantFragmentToMealFragment(meal.id)
                it.findNavController().navigate(action)
            }
        }
    }

    fun setMealsList(meals: ArrayList<Meal>) {
        mealsList = meals
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = mealsList.size

    inner class MealsViewHolder(val binding: ItemRestaurantMealBinding): RecyclerView.ViewHolder(binding.root)
}