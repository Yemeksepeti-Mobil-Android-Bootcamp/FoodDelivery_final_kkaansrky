package com.example.fooddelivery.ui.meal

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.FragmentMealBinding
import com.example.fooddelivery.utils.Resource
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealFragment : Fragment() {
    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MealViewModel by viewModels()
    private val args: MealFragmentArgs by navArgs()

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
        setMealDetails()
    }

    private fun setMealDetails() {
        viewModel.getMealDetails(args.mealId).observe(viewLifecycleOwner,{
            when (it.status) {
                Resource.Status.LOADING -> {
                    //binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    //binding.progressBar.visibility = View.GONE
                    val meal = it.data!!.data

                    binding.apply {
                        mealNameTextView.text = meal.name
                        mealDescriptionTextView.text = meal.description
                        val price = meal.price + " TL"
                        mealPriceTextView.text = price

                        Glide
                            .with(requireContext())
                            .load(meal.image)
                            .placeholder(R.drawable.temp_meal)
                            .into(mealImageView)

                        setIngredientsChips(meal.ingredients)
                    }
                }
                Resource.Status.ERROR -> {
                    //binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, "The restaurant could not be brought", Toast.LENGTH_SHORT).show()
                }
            }
        })
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
            mChip.setOnClickListener{
                if (mChip.paintFlags == Paint.STRIKE_THRU_TEXT_FLAG){
                    mChip.paintFlags = 0
                }else {
                    mChip.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
            }

            binding.chipGroup.addView(mChip)
        }
    }
}