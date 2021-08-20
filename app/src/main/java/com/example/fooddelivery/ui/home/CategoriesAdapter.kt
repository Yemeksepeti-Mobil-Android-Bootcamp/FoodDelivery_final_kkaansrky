package com.example.fooddelivery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ItemHomeCategoryBinding
import com.example.fooddelivery.ui.home.CategoriesAdapter.*

class CategoriesAdapter: RecyclerView.Adapter<CategoriesViewHolder>() {

    private var categoriesList = ArrayList<String>()

    private var listener: ICategoryOnClick? = null
    private var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemHomeCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categoriesList[position]
        holder.binding.categoryTextView.text = category

        with(category.lowercase()){
            when{
                contains("burger")->holder.binding.categoryImageView.setImageResource(R.drawable.ic_hamburger)
                contains("salad")->holder.binding.categoryImageView.setImageResource(R.drawable.ic_salad)
                contains("pizza")->holder.binding.categoryImageView.setImageResource(R.drawable.ic_pizza)
                else -> holder.binding.categoryImageView.setImageResource(R.drawable.ic_temp_category)
            }
        }

        holder.binding.itemCardView.setOnClickListener {
            selectedItem= holder.adapterPosition

            listener?.let {
                listener?.categoryOnClick(position)
            }

            notifyDataSetChanged()
        }
    }

    fun setCategoriesList(categories: ArrayList<String>) {
        categoriesList = categories
        notifyDataSetChanged()
    }

    fun addListener(listener: ICategoryOnClick) {
        this.listener = listener
    }


    override fun getItemCount(): Int = categoriesList.size

    inner class CategoriesViewHolder(val binding: ItemHomeCategoryBinding):RecyclerView.ViewHolder(binding.root)
}


