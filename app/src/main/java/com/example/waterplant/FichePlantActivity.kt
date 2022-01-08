package com.example.waterplant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.waterplant.databinding.ActivityPlantFicheBinding
import com.example.waterplant.entities.Plant
import com.example.waterplant.room.IdPlant
import com.example.waterplant.viewmodels.MainViewModel

class FichePlantActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPlantFicheBinding .inflate( layoutInflater ) }
    private val model  by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}
    private lateinit var plant:Plant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        this.plant =  intent.getSerializableExtra("plant_id") as Plant

        binding.plantName.text = plant.name
        binding.nameLatin.text = plant.latinName
        binding.prochainArro.text = plant.lastArosage

        binding.suppBtn.setOnClickListener {
            deletePlant()
            finish()
        }

        binding.modifBtn.setOnClickListener {
            finish()
        }

        binding.modifBtn.setOnClickListener {
            finish()
        }

        binding.back.setOnClickListener {
            finish()
        }

        binding.backText.setOnClickListener {
            finish()
        }
    }

    fun deletePlant(){
        model.deletePlant(plant.id)
        Toast.makeText(this,"Suppression effectue avec success",Toast.LENGTH_LONG).show()
    }

}