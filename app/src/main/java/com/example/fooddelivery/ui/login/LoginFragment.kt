package com.example.fooddelivery.ui.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.FragmentLoginBinding
import com.example.fooddelivery.ui.main.MainActivity
import com.example.fooddelivery.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {

        binding.loginButton.setOnClickListener{

            postLoginRequest()

        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    private fun postLoginRequest() {
        val email = binding.loginEmailEditText.text.toString()
        val password = binding.loginPasswordEditText.text.toString()

        viewModel.login(email, password).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    //_binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    //_binding.progressBar.gone()

                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()

                }
                Resource.Status.ERROR -> {
                    //_binding.progressBar.gone()
                    val dialog = AlertDialog.Builder(context)
                        .setTitle("Error")
                        .setMessage("${it.message}")
                        .setPositiveButton("ok") { dialog, button ->
                            dialog.dismiss()
                        }
                    dialog.show()


                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}