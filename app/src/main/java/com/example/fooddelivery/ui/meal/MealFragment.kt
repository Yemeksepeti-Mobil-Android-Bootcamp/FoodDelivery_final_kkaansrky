package com.example.fooddelivery.ui.meal

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.meal.Meal
import com.example.fooddelivery.databinding.FragmentMealBinding
import com.example.fooddelivery.utils.Resource
import com.example.fooddelivery.utils.gone
import com.example.fooddelivery.utils.show
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealFragment : Fragment() {
    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MealViewModel by viewModels()
    private val args: MealFragmentArgs by navArgs()

    private var orderCount = 1
    private var mealPrice = 1f

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
        setOrderCountCardListener()
    }

    private fun setMealDetails() {
        viewModel.getMealDetails(args.mealId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.materialCard.gone()
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.materialCard.show()
                    binding.progressBar.gone()
                    val meal = it.data!!.data

                    binding.apply {
                        mealNameTextView.text = meal.name
                        mealDescriptionTextView.text = meal.description
                        mealPrice = meal.price.toFloat()
                        setMealPriceText()

                        Glide
                            .with(requireContext())
                            .load(meal.image)
                            .placeholder(R.drawable.temp_meal)
                            .into(mealImageView)

                        setIngredientsChips(meal.ingredients)

                        setOrderButtonListener(meal)
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.gone()
                    Toast.makeText(
                        activity,
                        "The restaurant could not be brought",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setOrderButtonListener(meal :Meal) {
        binding.apply {
            mealOrderButton.setOnClickListener {
                val localOrderList = viewModel.getOrderFromRoomDb()
                meal.quantity = orderCount

                val ingredients = ArrayList<String>()
                chipGroup.children.toList()
                    .filter { !(it as Chip).isChecked }
                    .forEach {
                        ingredients.add((it as Chip).text.toString())
                    }

                meal.ingredients = ingredients

                //Burası aşırı spagetti oldu ama amacım eğer orderda zaten bu yemek varsa
                // o yemeğin countunu sipariş edilmek istenen kadar arttırmaktı.
                val findOrder = localOrderList.meals.find { it.id == meal.id && it.ingredients == meal.ingredients }
                if (findOrder != null) {
                    localOrderList.meals.find { it.id == meal.id && it.ingredients == meal.ingredients }!!.quantity += orderCount
                } else {
                    localOrderList.meals = localOrderList.meals.plus(meal)
                }

                viewModel.setOrderInRoomDb(localOrderList)

                Toast.makeText(
                    activity,
                    "Added Meal to Card",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setIngredientsChips(ingredients: ArrayList<String>) {
        for (ingredient in ingredients) {
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

    private fun setOrderCountCardListener() {
        binding.apply {
            mealPlusButton.setOnClickListener {
                orderCount++
                setOrderCountText(orderCount)
            }

            mealMinusButton.setOnClickListener {
                if (orderCount > 1) {
                    orderCount--
                    setOrderCountText(orderCount)
                }
            }
        }
    }

    private fun setOrderCountText(orderCount: Int) {
        binding.mealCountTextView.text = orderCount.toString()
        setMealPriceText()
    }

    private fun setMealPriceText() {
        val priceText = (mealPrice * orderCount).toString() + " TL"
        binding.mealPriceTextView.text = priceText
    }
}