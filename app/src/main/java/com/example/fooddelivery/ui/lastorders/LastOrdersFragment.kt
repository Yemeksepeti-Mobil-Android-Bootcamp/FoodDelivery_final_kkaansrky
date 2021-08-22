package com.example.fooddelivery.ui.lastorders

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelivery.data.entity.order.OrderRestaurant
import com.example.fooddelivery.databinding.FragmentLastOrdersBinding
import com.example.fooddelivery.utils.Resource
import com.example.fooddelivery.utils.gone
import com.example.fooddelivery.utils.show
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LastOrdersFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentLastOrdersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LastOrdersViewModel by viewModels()
    private val adapter: LastOrdersAdapter = LastOrdersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLastOrdersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val restaurantList = ArrayList<OrderRestaurant>()
        viewModel.getOrders().observe(viewLifecycleOwner,{
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                    for (item in it.data!!.orderList){
                        restaurantList.add(item.restaurant)
                    }
                    setRestaurantsListInAdapter(restaurantList)
                    Log.d(TAG, "initViews: "+it)
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.gone()
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

    private fun setRestaurantsListInAdapter(restaurantsList: ArrayList<OrderRestaurant>){
        binding.orderRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.setRestaurants(restaurantsList)

        binding.orderRecycler.adapter = adapter
    }
}
