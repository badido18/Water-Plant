package com.example.waterplant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waterplant.databinding.ActivityArrosageBinding

class ArrosageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityArrosageBinding // binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityArrosageBinding.inflate(layoutInflater) // binding
        setContentView(binding.root) // binding


        binding.back.setOnClickListener {
            finish()
        }
    }
}