package com.example.waterplant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.waterplant.databinding.ActivityArrosageBinding
import com.example.waterplant.databinding.ActivityDecalerBinding

class DecalerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDecalerBinding // binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDecalerBinding.inflate(layoutInflater) // binding
        setContentView(binding.root) // binding


        binding.btnAnnuler.setOnClickListener {
            finish()
        }

    }


    fun back(v: View) {
        finish()
    }
}