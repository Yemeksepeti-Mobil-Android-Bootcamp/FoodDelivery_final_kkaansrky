package com.example.fooddelivery.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val FRAGMENT_COUNT = 3

class OnboardingAdapter(requireActivity: FragmentActivity) : FragmentStateAdapter(requireActivity) {

    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstOnboardingFragment()
            1 -> SecondOnboardingFragment()
            2 -> ThirdOnboardingFragment()
            else -> FirstOnboardingFragment()
        }
    }

}