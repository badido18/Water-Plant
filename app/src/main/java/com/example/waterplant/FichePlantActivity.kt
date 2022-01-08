package com.example.waterplant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.waterplant.databinding.ActivityPlantFicheBinding
import com.example.waterplant.room.IdPlant
import com.example.waterplant.viewmodels.MainViewModel

class FichePlantActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPlantFicheBinding .inflate( layoutInflater ) }
    private val model  by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}
    private lateinit var plantId:IdPlant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        this.plantId = savedInstanceState?.get("plant_id") as IdPlant

        binding.suppBtn.setOnClickListener {
            deletePlant()
        }

        binding.back.setOnClickListener {
            finish()
        }
    }

    fun deletePlant(){
        model.deletePlant(plantId.id)
    }

}