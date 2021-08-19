package com.example.fooddelivery.ui.main

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewPagerAdapter: MainViewPagerAdapter


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
                        Log.d(TAG, "onCreate: Girdi")
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
        viewPagerAdapter = MainViewPagerAdapter(this)
        binding.viewPager2.apply {
            isUserInputEnabled = false
            adapter = viewPagerAdapter
        }
    }
}