package com.example.fooddelivery.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.meal.Meal
import com.example.fooddelivery.databinding.FragmentRestaurantBinding
import com.example.fooddelivery.ui.addmeal.AddMealFragment
import com.example.fooddelivery.utils.Resource
import com.example.fooddelivery.utils.gone
import com.example.fooddelivery.utils.hide
import com.example.fooddelivery.utils.show
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment() {
    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RestaurantViewModel by viewModels()
    private val args: RestaurantFragmentArgs by navArgs()

    private var mealsAdapter: MealsAdapter = MealsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setAppBarLayoutVisible()
        setRestaurantDetails()
        setMealAddButtonAdminVisibility()
    }

    private fun setAppBarLayoutVisible() {
        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            when {
                //  State Expanded
                verticalOffset == 0 -> {
                    binding.collapsingRelativeLayout.show()
                }
                verticalOffset != 0 -> {
                    binding.collapsingRelativeLayout.hide()
                }
            }
        })
    }

    private fun setRestaurantDetails() {
        viewModel.getRestaurantDetail(args.restaurantId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    //binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    //binding.progressBar.visibility = View.GONE
                    val restaurant = it.data!!.data
                    binding.apply {
                        restaurantNameTextView.text = restaurant.name
                        deliveryInfoTextView.text = restaurant.deliveryInfo

                        deliveryTimeTextView.text = restaurant.deliveryTime
                        minimumDeliveryFeeTextView.text = restaurant.minimumDeliveryFee
                        paymentTextView.text = restaurant.paymentMethods

                        Glide
                            .with(requireContext())
                            .load(restaurant.image)
                            .placeholder(R.drawable.ic_restaurant_temp_photo)
                            .into(restaurantImageView)

                        viewModel.addRestaurantIdInRoom(restaurant.id)
                        setMealsRecyclerView(restaurant.meals)
                    }
                }
                Resource.Status.ERROR -> {
                    //binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        activity,
                        "Not retrieve restaurants",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }

    private fun setMealsRecyclerView(mealslist: ArrayList<Meal>) {
        binding.restaurantRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mealsAdapter.setMealsList(mealslist)

        binding.restaurantRecyclerView.adapter = mealsAdapter
    }

    private fun setMealAddButtonAdminVisibility() {
        viewModel.getUser().observe(viewLifecycleOwner,{
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                    val role = it.data!!.user.role
                    if (role == "admin"){
                        binding.addMealButton.show()
                        setMealAddButton()
                    }else{
                        binding.addMealButton.gone()
                    }
                }
                Resource.Status.ERROR ->{
                    binding.progressBar.gone()

                }

            }
        })
    }

    private fun setMealAddButton() {
        binding.addMealButton.setOnClickListener {
            val dialog = AddMealFragment(args.restaurantId)
            dialog.show(requireActivity().supportFragmentManager,"Add Meal")
        }
    }
}
