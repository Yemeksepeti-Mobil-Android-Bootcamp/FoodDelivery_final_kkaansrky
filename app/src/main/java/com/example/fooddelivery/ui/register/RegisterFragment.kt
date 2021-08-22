package com.example.fooddelivery.ui.register

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.fooddelivery.databinding.FragmentRegisterBinding
import com.example.fooddelivery.ui.main.MainActivity
import com.example.fooddelivery.utils.Resource
import com.example.fooddelivery.utils.hide
import com.example.fooddelivery.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {

        binding.registerButton.setOnClickListener {

            postRegisterRequest()
        }
    }

    private fun postRegisterRequest() {
        val name = binding.registerNameEditText.text.toString()
        val email = binding.registerEmailEditText.text.toString()
        val password = binding.registerPasswordEditText.editText?.text.toString()

        viewModel.register(name,email, password).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.registerEmailEditText.hide()
                    binding.registerPasswordEditText.hide()
                    binding.registerNameEditText.hide()
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    //_binding.progressBar.gone()

                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                Resource.Status.ERROR -> {
                    binding.registerEmailEditText.show()
                    binding.registerPasswordEditText.show()
                    binding.registerNameEditText.show()
                    binding.progressBar.hide()

                    val dialog = AlertDialog.Builder(context)
                        .setTitle("Error")
                        .setMessage("Register Failed")
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