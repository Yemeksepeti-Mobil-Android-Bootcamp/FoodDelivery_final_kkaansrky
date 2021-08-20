package com.example.fooddelivery.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val adapter = OnboardingAdapter(requireActivity())

        binding.apply {
            viewPager2.adapter = adapter
            wormDots.setViewPager2(binding.viewPager2)

            cardViewButton.setOnClickListener {

                //Eğer son onboarding sayfasına geldiyse diye kontrol ediyoruz.
                if (binding.viewPager2.currentItem == adapter.itemCount-1) {
                    saveOnBoardingFinished()
                    findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
                } else {
                    binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
                }

            }
        }
    }

    private fun saveOnBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("finished", true)
        editor.apply()
    }
}