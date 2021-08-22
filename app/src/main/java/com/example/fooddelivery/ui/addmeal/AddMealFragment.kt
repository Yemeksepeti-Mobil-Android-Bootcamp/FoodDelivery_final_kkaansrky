package com.example.fooddelivery.ui.addmeal

import android.content.ContentValues.TAG
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.mealadd.MealAddRequest
import com.example.fooddelivery.databinding.FragmentAddMealBinding
import com.example.fooddelivery.ui.restaurant.RestaurantFragmentDirections
import com.example.fooddelivery.utils.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMealFragment(restaurantId: String) : BottomSheetDialogFragment() {
    private var _binding: FragmentAddMealBinding? = null
    private val binding get() = _binding!!

    private val restaurantId = restaurantId

    private val viewModel: AddMealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMealBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setAddIngredientsOnClickListener()
        setAddMealOnClickListener()
    }

    private fun setAddMealOnClickListener() {

        binding.apply {
            addMealButton.setOnClickListener {
                if (checkEditTextsNull()) {
                    val ingredients = ArrayList<String>()
                    chipGroup.children.toList()
                        .filter { !(it as Chip).isChecked }
                        .forEach {
                            ingredients.add((it as Chip).text.toString())
                        }
                    viewModel.postMeal(
                        restaurantId, MealAddRequest(
                            mealNameEditText.text.toString(),
                            mealImageUrlEditText.text.toString(),
                            mealDescriptionEditText.text.toString(),
                            mealPriceEditText.text.toString().toDouble(),
                            ingredients,
                        )
                    ).observe(
                        viewLifecycleOwner,
                        {
                            when (it.status) {
                                Resource.Status.LOADING -> {
                                    //binding.progressBar.visibility = View.VISIBLE
                                }
                                Resource.Status.SUCCESS -> {
                                    Toast.makeText(
                                        activity,
                                        "Meal Added",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    dismiss()
                                    val action = RestaurantFragmentDirections.actionRestaurantFragmentSelf(restaurantId)
                                    findNavController().navigate(action)
                                }
                                Resource.Status.ERROR -> {
                                    //binding.progressBar.visibility = View.GONE
                                    Toast.makeText(
                                        activity,
                                        "Meal Add Error",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Log.d(TAG, "setAddMealOnClickListener: "+it)
                                }
                            }
                        })
                }
            }
        }
    }


    private fun checkEditTextsNull(): Boolean {
        binding.apply {
            return when {
                mealNameEditText.text.isNullOrEmpty() -> {
                    false
                }
                mealImageUrlEditText.text.isNullOrEmpty() -> {
                    false
                }
                mealDescriptionEditText.text.isNullOrEmpty() ->{
                    false
                }
                mealPriceEditText.text.isNullOrEmpty() -> {
                    false
                }
                else -> chipGroup.childCount != 0
            }
        }
    }

    private fun setAddIngredientsOnClickListener() {
        binding.apply {
            addIngredients.setOnClickListener {
                if (!ingredientsEditText.text.isNullOrEmpty()) {
                    addIngredients(ingredientsEditText.text.toString())
                    ingredientsEditText.text.clear()
                }
            }
        }
    }

    private fun addIngredients(ingredient: String) {
        val mChip =
            this.layoutInflater.inflate(R.layout.item_meal_ingredient, null, false) as Chip
        mChip.setText(ingredient)
        mChip.setOnClickListener {
            if (mChip.paintFlags == Paint.STRIKE_THRU_TEXT_FLAG) {
                mChip.paintFlags = 0
            } else {
                mChip.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        binding.chipGroup.addView(mChip)
    }
}