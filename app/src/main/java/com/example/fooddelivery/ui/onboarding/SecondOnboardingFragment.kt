package com.example.fooddelivery.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fooddelivery.databinding.FragmentSecondOnboardingBinding

class SecondOnboardingFragment : Fragment() {
    private var _binding: FragmentSecondOnboardingBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondOnboardingBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }
}