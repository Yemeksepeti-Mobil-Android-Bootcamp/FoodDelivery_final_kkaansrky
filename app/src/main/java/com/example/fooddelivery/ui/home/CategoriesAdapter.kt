package com.example.fooddelivery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.Categorie
import com.example.fooddelivery.databinding.ItemHomeCategoriesBinding
import com.example.fooddelivery.ui.home.CategoriesAdapter.*

class CategoriesAdapter: RecyclerView.Adapter<CategoriesViewHolder>() {

    private var categoriesList = ArrayList<Categorie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemHomeCategoriesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val categorie = categoriesList[position]
        holder.binding.categoriesTextView.text = categorie.categorieName

        when(categorie.categorieImage){
            1->holder.binding.categoriesImageView.setImageResource(R.drawable.ic_hamburger)
            2->holder.binding.categoriesImageView.setImageResource(R.drawable.ic_salad)
            3->holder.binding.categoriesImageView.setImageResource(R.drawable.ic_pizza)
        }
    }

    fun setQuizList(categories: ArrayList<Categorie>) {
        categoriesList = categories
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = categoriesList.size

    inner class CategoriesViewHolder(val binding: ItemHomeCategoriesBinding):RecyclerView.ViewHolder(binding.root)
}


