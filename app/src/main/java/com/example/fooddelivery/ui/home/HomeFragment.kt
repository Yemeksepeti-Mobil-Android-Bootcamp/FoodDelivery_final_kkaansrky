package com.example.fooddelivery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelivery.data.entity.restaurant.Restaurant
import com.example.fooddelivery.databinding.FragmentHomeBinding
import com.example.fooddelivery.ui.addrestaurant.AddRestaurantFragment
import com.example.fooddelivery.utils.Resource
import com.example.fooddelivery.utils.gone
import com.example.fooddelivery.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(),ICategoryOnClick {

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

        categoriesAdapter.addListener(this)

        binding.homeCategoriesRecyclerView.adapter = categoriesAdapter
        binding.homeRestaurantRecyclerView.adapter = restaurantAdapter

        getRestaurantsListFromViewModel()
        setSearchViewListener()
        setRestaurantAddButtonAdminVisibility()
    }

    private fun getRestaurantsListFromViewModel() {
        viewModel.getRestaurantsList().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    //binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    //binding.progressBar.visibility = View.GONE
                    val restaurantsListResponse = it.data
                    viewModel.restaurantsList = restaurantsListResponse?.restaurantList
                    setRestaurantsListInAdapter(viewModel.restaurantsList as ArrayList<Restaurant>)

                    setCategoriesListInAdapter(viewModel.getAndSetCategoryListFromRestaurants())

                }
                Resource.Status.ERROR -> {
                    //binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, "Not retrieve restaurants", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setCategoriesListInAdapter(categorieslist: ArrayList<String>) {
        categoriesAdapter.setCategoriesList(categorieslist)
    }

    private fun setSearchViewListener() {
        binding.homeSearchEditText.addTextChangedListener {
            getSearchRestaurantsResultFromViewModel(it.toString())
        }
    }

    private fun getSearchRestaurantsResultFromViewModel(searchText :String) {
        val filteredRestaurantsList = viewModel.searchRestaurantsGetFilteredList(searchText)
        setRestaurantsListInAdapter(filteredRestaurantsList as ArrayList<Restaurant>)
    }

    private fun setRestaurantAddButtonAdminVisibility() {
        viewModel.getUser().observe(viewLifecycleOwner,{
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                   val role = it.data!!.user.role
                    if (role == "admin"){
                        binding.addRestaurantButton.show()
                        setRestaurantAddButton()
                    }else{
                        binding.addRestaurantButton.gone()
                    }
                }
                Resource.Status.ERROR ->{
                    binding.progressBar.gone()
                }
            }
        })
    }

    private fun setRestaurantAddButton() {
        binding.addRestaurantButton.setOnClickListener {
            val dialog = AddRestaurantFragment()
            dialog.show(requireActivity().supportFragmentManager,"Add Restaurant")
        }
    }

    override fun categoryOnClick(position: Int) {
        getRestaurantsOrderByCategory(position)
    }

    private fun getRestaurantsOrderByCategory(currentPosition: Int) {
        //All restaurants index = 0
        if (currentPosition == 0){
            getRestaurantsListFromViewModel()
        }else {
            viewModel.getRestaurantByCuisine(viewModel.getAndSetCategoryListFromRestaurants()[currentPosition]).observe(viewLifecycleOwner, { response ->
                when (response.status) {
                    Resource.Status.LOADING -> {
                        //binding.progressBar.show()
                    }
                    Resource.Status.SUCCESS -> {
                        viewModel.restaurantsList = response.data?.restaurantList
                        setRestaurantsListInAdapter(response.data?.restaurantList as ArrayList<Restaurant>)
                    }
                    Resource.Status.ERROR ->{
                        Toast.makeText(activity, "Not retrieve restaurants", Toast.LENGTH_SHORT).show()
                    }

                }
            })
        }
    }

    private fun setRestaurantsListInAdapter(restaurantsList: ArrayList<Restaurant>){
        restaurantAdapter.setRestaurantsList(restaurantsList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}