package com.example.fooddelivery.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelivery.databinding.FragmentRestaurantBinding
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment() {
    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RestaurantViewModel by viewModels()

    private var mealsAdapter: MealsAdapter = MealsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        binding.restaurantRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val mealslist = viewModel.getTestItemAddMealsList()
        mealsAdapter.setMealsList(mealslist)

        binding.restaurantRecyclerView.adapter=mealsAdapter

        binding.appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                when {
                    //  State Expanded
                    verticalOffset == 0 -> {
                        binding.collapsingRelativeLayout.visibility = View.VISIBLE
                    }
                    verticalOffset != 0 ->{
                        binding.collapsingRelativeLayout.visibility = View.INVISIBLE
                    }
                }
            }
        })
    }
}
