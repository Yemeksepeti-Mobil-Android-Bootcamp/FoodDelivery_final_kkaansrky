package com.example.fooddelivery.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.auth0.android.jwt.JWT
import com.example.fooddelivery.R
import com.example.fooddelivery.data.local.SharedPrefManager
import com.example.fooddelivery.databinding.FragmentSplashBinding
import com.example.fooddelivery.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root

        initViews()

        return view
    }

    private fun initViews() {
        binding.splashAnimation.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {

                val token = getTokenFromLocal()
                if (!token.isNullOrEmpty()) {
                    val jwt = JWT(token)
                    if (!jwt.isExpired(0)) {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }else{
                        goToLoginFragment()
                    }
                }else{
                    goToLoginFragment()
                }
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationRepeat(animation: Animator?) {}
        })
    }

    private fun goToLoginFragment(){
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }

    private fun getTokenFromLocal(): String? {
        return SharedPrefManager(requireContext()).getToken()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}