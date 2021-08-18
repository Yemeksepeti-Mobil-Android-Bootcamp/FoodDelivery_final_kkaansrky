package com.example.fooddelivery.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ActivityMainBinding
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: MainViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setViewPager()
        binding.apply {
            mainNavBar.setItemSelected(R.id.homeFragment)
            mainNavBar.showBadge(R.id.restaurantFragment,2)

            mainNavBar.setOnItemSelectedListener {
                when(it){
                    R.id.homeFragment->{
                        binding.viewPager2.currentItem = 0
                    }
                    R.id.restaurantFragment->{
                        binding.viewPager2.currentItem = 1
                    }
                    else ->{
                        binding.viewPager2.currentItem = 1
                    }
                }
            }

        }
    }

    private fun setViewPager() {
        adapter = MainViewPagerAdapter(this)
        binding.viewPager2.apply {
            isUserInputEnabled = false
            adapter = adapter
        }
    }
}