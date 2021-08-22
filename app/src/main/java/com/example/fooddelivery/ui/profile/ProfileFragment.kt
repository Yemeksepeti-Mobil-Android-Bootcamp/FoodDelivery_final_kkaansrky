package com.example.fooddelivery.ui.profile

import android.content.Intent
import android.os.Bundle
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
import com.example.fooddelivery.ui.lastorders.LastOrdersFragment
import com.example.fooddelivery.ui.splash.SplashActivity
import com.example.fooddelivery.ui.updateuser.UpdateUserFragment
import com.example.fooddelivery.utils.Resource
import com.example.fooddelivery.utils.gone
import com.example.fooddelivery.utils.show
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
        initListeners()
    }

    private fun initListeners() {
        binding.logOutButton.setOnClickListener {
            logOutUser()
        }

        binding.updateProfile.setOnClickListener {
            val dialog = UpdateUserFragment()
            dialog.show(requireActivity().supportFragmentManager,"Update User")
        }

        binding.lastOrders.setOnClickListener {
            val dialog = LastOrdersFragment()
            dialog.show(requireActivity().supportFragmentManager,"Last Orders")
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

            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                    val user = it.data?.user
                    if (user != null) {
                        updateUI(user)
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.gone()
                    Toast.makeText(activity, "Not retrieve user", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun updateUI(user: User) {
        binding.nameTextView.text = user.name
        binding.countryTextView.text = user.address
        binding.emailTextView.text = user.email

        Glide
            .with(requireContext())
            .load(user.profileImage)
            .placeholder(R.drawable.ic_home_profile)
            .into(binding.profilePhoto)
    }

}