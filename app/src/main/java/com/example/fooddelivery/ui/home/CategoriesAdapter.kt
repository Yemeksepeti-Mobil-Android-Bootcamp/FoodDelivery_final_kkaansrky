package com.example.fooddelivery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.Category
import com.example.fooddelivery.databinding.ItemHomeCategoryBinding
import com.example.fooddelivery.ui.home.CategoriesAdapter.*

class CategoriesAdapter: RecyclerView.Adapter<CategoriesViewHolder>() {

    private var categoriesList = ArrayList<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemHomeCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categoriesList[position]
        holder.binding.categoryTextView.text = category.categoryName

        when(category.categoryImage){
            1->holder.binding.categoryImageView.setImageResource(R.drawable.ic_hamburger)
            2->holder.binding.categoryImageView.setImageResource(R.drawable.ic_salad)
            3->holder.binding.categoryImageView.setImageResource(R.drawable.ic_pizza)
        }
    }

    fun setCategoriesList(categories: ArrayList<Category>) {
        categoriesList = categories
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = categoriesList.size

    inner class CategoriesViewHolder(val binding: ItemHomeCategoryBinding):RecyclerView.ViewHolder(binding.root)
}


