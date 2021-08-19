package com.example.fooddelivery.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fooddelivery.ui.home.HomeFragment
import com.example.fooddelivery.ui.profile.ProfileFragment
import com.example.fooddelivery.ui.restaurant.RestaurantFragment

private const val FRAGMENT_COUNT = 3

class MainViewPagerAdapter (requireActivity: FragmentActivity) : FragmentStateAdapter(requireActivity) {

    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> HomeFragment()
            2 -> ProfileFragment()
            else -> HomeFragment()
        }
    }
}
