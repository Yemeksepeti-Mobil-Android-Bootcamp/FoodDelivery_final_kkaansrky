package com.example.fooddelivery.ui.profile

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.user.User
import com.example.fooddelivery.databinding.FragmentProfileBinding
import com.example.fooddelivery.ui.login.LoginViewModel
import com.example.fooddelivery.ui.splash.SplashActivity
import com.example.fooddelivery.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewHolder by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileCard.setBackgroundResource(R.drawable.custom_cardview)
        initViews()
    }

    private fun initViews() {
        getUser()

        binding.logOutButton.setOnClickListener {
            logOutUser()
        }
    }

    private fun logOutUser() {
        viewModel.logOutUser()
        val intent = Intent(context, SplashActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun getUser() {
        viewModel.getUser().observe(viewLifecycleOwner, {

            Log.d(TAG, "getUser" + (it.data?.success))

            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    val user = it.data?.user
                    if (user != null) {
                        updateUI(user)
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, "Kullanıcı getirelemedi", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun updateUI(user: User) {
        binding.nameTextView.text = user.name
        binding.countryTextView.text = user.about
        binding.emailTextView.text = user.email

        Glide
            .with(requireContext())
            .load(user.profileImage)
            .placeholder(R.drawable.ic_home_profile)
            .into(binding.profilePhoto)
    }

}