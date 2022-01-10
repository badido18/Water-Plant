package com.example.waterplant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterplant.adapters.ArrosageAdapter
import com.example.waterplant.databinding.ActivityArrosageBinding
import com.example.waterplant.entities.Plant
import com.example.waterplant.viewmodels.ArrosageViewModel
import com.example.waterplant.viewmodels.MainViewModel

class ArrosageActivity : AppCompatActivity() {
    private val model by lazy { ViewModelProvider(this).get(ArrosageViewModel::class.java) }
    private val adapter by lazy { ArrosageAdapter(this, model) }
    private lateinit var binding: ActivityArrosageBinding // binding

    companion object {  /* définir les constantes */
        const val CHANNEL_ID = "phrases_channel"
        const val NOTIFICATION_ID = 9
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArrosageBinding.inflate(layoutInflater) // binding
        setContentView(binding.root) // binding

//        model.allArrosages = MutableLiveData()
//        model.loadArrosages()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        model.allArrosages.observe(this) {
            adapter.setArrosages(it)
        }

        binding.back.setOnClickListener {
            finish()
        }

    }

    fun back(v: View) {
        finish()
    }
}