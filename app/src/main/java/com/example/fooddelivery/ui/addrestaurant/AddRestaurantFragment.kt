package com.example.fooddelivery.ui.addrestaurant

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddelivery.databinding.FragmentAddRestaurantBinding
import com.example.fooddelivery.utils.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ThreadLocalRandom

@AndroidEntryPoint
class AddRestaurantFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentAddRestaurantBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddRestaurantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddRestaurantBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        initializeCuisineSpinner()
        initializePaymentMethodsSpinner()
        setAddButtonListener()
    }

    private fun setAddButtonListener() {
        binding.addRestaurantButton.setOnClickListener {
            if(checkNullEditText()){
                binding.apply {
                    viewModel.postRestuarant(RestaurantAddRequest(
                        restaurantNameEditText.text.toString(),
                        cuisineSpinner.selectedItem.toString(),
                        restaurantDeliveryInfoEditText.text.toString(),
                        restaurantDeliveryTimeEditText.text.toString(),
                        restaurantAddressEditText.text.toString(),
                        restaurantDistrictEditText.text.toString(),
                        restaurantMinDeliveryEditText.text.toString(),
                        paymentSpinner.selectedItem.toString(),
                        randomRating(),
                        restaurantImageUrlEditText.text.toString()
                    )).observe(viewLifecycleOwner,{
                        when (it.status) {
                            Resource.Status.LOADING -> {
                                //binding.progressBar.visibility = View.VISIBLE
                            }
                            Resource.Status.SUCCESS -> {
                                Toast.makeText(
                                    activity,
                                    "Restaurant Added",
                                    Toast.LENGTH_SHORT
                                ).show()

                                dismiss()
                                findNavController().navigate(R.id.action_homeFragment_self)
                            }
                            Resource.Status.ERROR -> {
                                //binding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    activity,
                                    "Restaurant Add Error",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d(TAG, "setAddButtonListener: "+ it)
                            }
                        }
                    })
                }

            }

        }
    }

    private fun randomRating(): Double {
        return ThreadLocalRandom.current().nextDouble(5.0, 10.0)
    }

    private fun checkNullEditText(): Boolean {
        binding.apply {
            if (restaurantNameEditText.text.isNullOrEmpty()){
                return false
            }else if (restaurantAddressEditText.text.isNullOrEmpty()){
                return false
            }else if (restaurantDeliveryInfoEditText.text.isNullOrEmpty()){
                return false
            }else if (restaurantDeliveryTimeEditText.text.isNullOrEmpty()){
                return false
            }else if (restaurantDistrictEditText.text.isNullOrEmpty()){
                return false
            }else if (restaurantMinDeliveryEditText.text.isNullOrEmpty()){
                return false
            }else if (restaurantImageUrlEditText.text.isNullOrEmpty()){
                return false
            }else{
                return true
            }
        }
    }

    private fun initializeCuisineSpinner() {
        val cuisine = resources.getStringArray(R.array.cuisines)
        val adapter = ArrayAdapter(
            activity as AppCompatActivity,
            R.layout.support_simple_spinner_dropdown_item,
            cuisine
        )
        binding.cuisineSpinner.adapter = adapter
    }

    private fun initializePaymentMethodsSpinner() {
        val paymentMethods = resources.getStringArray(R.array.RestaurantPaymentMethods)
        val adapter = ArrayAdapter(
            activity as AppCompatActivity,
            R.layout.support_simple_spinner_dropdown_item,
            paymentMethods
        )
        binding.paymentSpinner.adapter = adapter
    }

}