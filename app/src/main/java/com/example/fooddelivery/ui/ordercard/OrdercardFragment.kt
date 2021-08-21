package com.example.fooddelivery.ui.ordercard

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.meal.Meal
import com.example.fooddelivery.databinding.FragmentOrdercardBinding
import com.example.fooddelivery.ui.restaurant.MealsAdapter
import com.example.fooddelivery.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdercardFragment : Fragment() {
    private var _binding: FragmentOrdercardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OrdercardViewModel by viewModels()
    private var ordercardAdapter: OrdercardAdapter = OrdercardAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdercardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val ordersList = viewModel.getOrderFromRoomDb()
        val order = ordersList.get(0)

        setOrdersRecyclerView(ArrayList(order.meals))

        Log.d(TAG, "initViews: "+order.meals.size)

        viewModel.getRestaurantByID(order.restaurantID).observe(viewLifecycleOwner,{

            when (it.status) {
                Resource.Status.LOADING -> {
                    //binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    val restaurant = it.data!!.data

                    binding.apply {
                        restaurantNameTextView.text = restaurant.name
                    }
                }
                Resource.Status.ERROR -> {
                    //binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, "The restaurant could not be brought", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun setOrdersRecyclerView(ordersList: ArrayList<Meal>) {
        binding.orderRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        ordercardAdapter.setOrdersList(ordersList)

        binding.orderRecyclerView.adapter = ordercardAdapter
    }
}