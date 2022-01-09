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
import com.example.waterplant.entities.Plant
import com.example.waterplant.room.PlantItem


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val model by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val adapter by lazy { MainAdapter(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        model.loadPlants()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        model.plants.observe(this) {
            adapter.setPlants(it)
        }

        binding.addButton.setOnClickListener {
            var intent: Intent = Intent(this, CreatePlantActivity::class.java)
            startActivity(intent)
        }
    }


    fun testerArrosage(v: View) {
        Intent(this, ArrosageActivity::class.java).also {
            startActivity(it)
        }
    }

    fun initialiser(view: View) {
        val names = resources.getStringArray(R.array.name)
        val latinNames = resources.getStringArray(R.array.latinName)
        val freqArosages = resources.getIntArray(R.array.freqArosage)
        val lastArosages = resources.getStringArray(R.array.lastArosage)

        val freqNutriments = resources.getIntArray(R.array.freqNutriment)
        val lastNutriments = resources.getStringArray(R.array.lastNutriment)

        for (i in names.indices) {
            model.addPlant(
                PlantItem(
                    names[i],
                    latinNames[i],
                    freqArosages[i],
                    lastArosages[i],
                    freqNutriments[i],
                    lastNutriments[i]
                )
            )
        }
    }

}