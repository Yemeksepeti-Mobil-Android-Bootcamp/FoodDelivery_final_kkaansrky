package com.example.fooddelivery.ui.meal

import android.R.attr.category
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.Meal
import com.example.fooddelivery.databinding.FragmentMealBinding
import com.google.android.material.chip.Chip

class MealFragment : Fragment() {
    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mealCard.setBackgroundResource(R.drawable.custom_cardview)
        initViews()
    }

    private fun initViews() {
        setIngredientsChips(getTestItemAddIngredient())
    }

    private fun getTestItemAddIngredient(): ArrayList<String> {
        val ingredients = ArrayList<String>()
        ingredients.add("Turşu")
        ingredients.add("Mayonez")
        ingredients.add("Ketçap")
        ingredients.add("Domates")
        ingredients.add("Turşu")
        ingredients.add("Mayonez")
        ingredients.add("Ketçap")
        ingredients.add("Domates")
        ingredients.add("Turşu")
        ingredients.add("Mayonez")
        ingredients.add("Ketçap")
        ingredients.add("Domates")

        return ingredients
    }

    private fun setIngredientsChips(ingredients : ArrayList<String>) {
        for (ingredient in ingredients){
            val mChip =
                this.layoutInflater.inflate(R.layout.item_meal_ingredient, null, false) as Chip
            mChip.setText(ingredient)

            binding.chipGroup.addView(mChip)
        }
    }
}