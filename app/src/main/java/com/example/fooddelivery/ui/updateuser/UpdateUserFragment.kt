package com.example.fooddelivery.ui.updateuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fooddelivery.R
import com.example.fooddelivery.data.entity.user.UserRequest
import com.example.fooddelivery.databinding.FragmentUpdateUserBinding
import com.example.fooddelivery.utils.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateUserFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentUpdateUserBinding? = null
    private val binding get() = _binding!!


    private val viewModel: UpdateUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setUserDetails()
        setUpdateButtonClickListener()
    }

    private fun setUpdateButtonClickListener() {
        binding.apply {
            updateButton.setOnClickListener {
                if (checkEditTexts()) {
                    viewModel.updateUser(
                        UserRequest(
                            userEmailEditText.text.toString(),
                            userNameEditText.text.toString(),
                            userPlaceEditText.text.toString(),
                            userPhoneEditText.text.toString(),
                            userImageUrlEditText.text.toString()
                        )
                    ).observe(viewLifecycleOwner, {
                        when (it.status) {
                            Resource.Status.LOADING -> {
                                //binding.progressBar.visibility = View.VISIBLE
                            }
                            Resource.Status.SUCCESS -> {
                                Toast.makeText(
                                    activity,
                                    "User Updated",
                                    Toast.LENGTH_SHORT
                                ).show()

                                dismiss()
                                findNavController().navigate(R.id.action_profileFragment_self)
                            }
                            Resource.Status.ERROR -> {
                                //binding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    activity,
                                    "User error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                }
            }
        }
    }

    private fun checkEditTexts(): Boolean {
        binding.apply {
            return when {
                userNameEditText.text.isNullOrEmpty() -> false
                userEmailEditText.text.isNullOrEmpty() -> false
                else -> !userImageUrlEditText.text.isNullOrEmpty()
            }
        }
    }

    private fun setUserDetails() {
        viewModel.getUser().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    //binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    val user = it.data!!.user

                    binding.apply {
                        userNameEditText.setText(user.name)
                        userEmailEditText.setText(user.email)
                        userPhoneEditText.setText(user.phone)
                        userPlaceEditText.setText(user.address)
                        userImageUrlEditText.setText(user.profileImage)
                    }

                }
                Resource.Status.ERROR -> {
                    //binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        activity,
                        "get User Error",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        })
    }
}

