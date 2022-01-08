package com.example.waterplant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterplant.adapters.MainAdapter
import com.example.waterplant.viewmodels.MainViewModel
import com.example.waterplant.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }
    private val model  by lazy {ViewModelProvider(this).get(MainViewModel::class.java)}
    private val adapter by lazy { MainAdapter(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        model.loadPlants()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        model.plants.observe(this){
            adapter.setPlants(it)
        }

        binding.addButton.setOnClickListener {
            var intent: Intent = Intent(this, CreatePlantActivity::class.java)
            startActivity(intent)
        }
    }


    fun testerArrosage(v: View)  {
        Intent(this, ArrosageActivity::class.java).also {
            startActivity(it)
        }
    }
}