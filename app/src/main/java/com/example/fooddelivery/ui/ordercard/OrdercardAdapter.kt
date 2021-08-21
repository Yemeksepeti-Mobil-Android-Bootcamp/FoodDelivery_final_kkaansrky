package com.example.fooddelivery.ui.ordercard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.meal.Meal
import com.example.fooddelivery.databinding.ItemOrderBinding

class OrdercardAdapter : RecyclerView.Adapter<OrdercardAdapter.OrdercardViewHolder>() {

    private var ordersList = ArrayList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdercardViewHolder {
        val binding = ItemOrderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdercardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdercardViewHolder, position: Int) {
        val order = ordersList[position]

        holder.binding.apply {
            mealNameTextView.text = order.name
            mealDescriptionTextView.text = order.ingredients.toString()
            val priceText = (order.price.toFloat() * order.quantity).toString() + " TL"
            mealPriceTextView.text = priceText
            val countText = order.quantity.toString() + " quantity"
            mealCountTextView.text = countText

            Glide
                .with(holder.itemView.context)
                .load(order.image)
                .placeholder(R.drawable.temp_meal)
                .into(mealImageView)

        }


    }

    fun setOrdersList(orders: ArrayList<Meal>) {
        ordersList = orders
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = ordersList.size

    inner class OrdercardViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root)
}