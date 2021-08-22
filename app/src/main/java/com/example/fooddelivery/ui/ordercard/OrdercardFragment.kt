package com.example.fooddelivery.ui.ordercard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.meal.Meal
import com.example.fooddelivery.databinding.FragmentOrdercardBinding
import com.example.fooddelivery.utils.Resource
import com.example.fooddelivery.utils.room.LocalOrder
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
        checkOrderAndUpdateUI()
    }

    private fun checkOrderAndUpdateUI() {
        val order = viewModel.getLocalOrderById()
        if (order !=null){
            val localMealList = order.meals
            if (localMealList.isNotEmpty()) {

                setOrdersRecyclerView(ArrayList(localMealList))
                setPriceTextView(localMealList)
                setRestaurantNameTextView(order.restaurantID)

                setOrderNowButtonClickListener(order.restaurantID, localMealList)
            } else {
                Toast.makeText(
                    activity,
                    "Card Empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }else {
            Toast.makeText(
                activity,
                "Card Empty",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setOrdersRecyclerView(ordersList: ArrayList<Meal>) {
        binding.orderRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        ordercardAdapter.setOrdersList(ordersList)

        binding.orderRecyclerView.adapter = ordercardAdapter
    }

    private fun setPriceTextView(localMealList: List<Meal>) {
        var topPrice = 0f
        for (item in localMealList) {
            topPrice = topPrice.plus((item.quantity * item.price.toFloat()))
        }

        val priceText = topPrice.toString() + " TL"
        binding.priceTextView.text = priceText
    }

    private fun setRestaurantNameTextView(restaurantID: String) {
        viewModel.getRestaurantByID(restaurantID).observe(viewLifecycleOwner, {
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
                    Toast.makeText(
                        activity,
                        "Not retrieve restaurant Name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setOrderNowButtonClickListener(restaurantID: String, localMealList: List<Meal>) {
        binding.orderButton.setOnClickListener {
            viewModel.setOrdersRequestObjectAndPost(restaurantID, localMealList)
                .observe(viewLifecycleOwner, {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            //binding.progressBar.visibility = View.VISIBLE
                        }
                        Resource.Status.SUCCESS -> {
                            Toast.makeText(
                                activity,
                                "Order Complete",
                                Toast.LENGTH_SHORT
                            ).show()

                            viewModel.setOrderInRoomDb(LocalOrder("1", "", emptyList()))
                            findNavController().navigate(R.id.action_ordercardFragment_self)
                        }
                        Resource.Status.ERROR -> {
                            //binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                activity,
                                "Order Error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })

        }
    }
}