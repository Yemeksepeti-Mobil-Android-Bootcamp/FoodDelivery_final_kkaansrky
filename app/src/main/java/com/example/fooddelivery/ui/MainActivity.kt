package com.example.fooddelivery.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mainNavBar.apply {
            setItemSelected(R.id.home)
            showBadge(R.id.basket,2)

            setOnItemSelectedListener {
                when (it) {

                    R.id.home -> {

                    }
                    R.id.basket -> {

                    }
                    R.id.person -> {

                    }
                }
            }
        }
    }
}