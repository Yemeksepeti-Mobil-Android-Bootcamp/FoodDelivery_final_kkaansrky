package com.example.fooddelivery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelivery.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private var categoriesAdapter: CategoriesAdapter = CategoriesAdapter()
    private var restaurantAdapter: RestaurantsAdapter = RestaurantsAdapter()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.homeCategoriesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.homeRestaurantRecyclerView.layoutManager =
            GridLayoutManager(context,2)

        val categorieslist = viewModel.getTestItemAddCategoriesList()
        categoriesAdapter.setCategoriesList(categorieslist)

        val restaurantsList = viewModel.getTestItemAddRestaurantList()
        restaurantAdapter.setQuizList(restaurantsList)


        binding.homeCategoriesRecyclerView.adapter = categoriesAdapter
        binding.homeRestaurantRecyclerView.adapter = restaurantAdapter
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}