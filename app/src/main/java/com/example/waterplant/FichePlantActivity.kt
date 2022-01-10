package com.example.waterplant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.waterplant.databinding.ActivityPlantFicheBinding
import com.example.waterplant.entities.Plant
import com.example.waterplant.utils.ImageManager
import com.example.waterplant.utils.TimeManager
import com.example.waterplant.utils.TimeManager.Companion.NEVER
import com.example.waterplant.viewmodels.MainViewModel

class FichePlantActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPlantFicheBinding .inflate( layoutInflater ) }
    private val model  by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}
    private lateinit var plant:Plant
    private val TM by lazy { TimeManager() }
    private val IM by lazy { ImageManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        this.plant =  intent.getSerializableExtra("plant_id") as Plant

        binding.plantName.text = plant.name
        binding.nameLatin.text = plant.latinName

        try {
            binding.plantImage.setImageBitmap(IM.getBitmap(plant.image!!))
        }
        catch (e:Exception){
            Log.d("exception","no iamge - put default")
        }

        if(plant.freqArosage == NEVER)
            binding.prochainArro.text = "non specifie"
        else
            binding.prochainArro.text = TM.nextDateToString(plant.lastArosage,plant.freqArosage)

        if(plant.freqNutriment == NEVER)
            binding.prochainNutri.text = "non specifie"
        else
            binding.prochainNutri.text = TM.nextDateToString(plant.lastNutriment, plant.freqNutriment)

        Log.d("FREQUENCE ARRSAE",plant.freqArosage.toString())

        binding.suppBtn.setOnClickListener {
            deletePlant()
            finish()
        }

        binding.modifBtn.setOnClickListener {
            Intent(this, ModifyActivity::class.java).also {
                it.putExtra("plant_id",plant)
                startActivity(it)
            }
            finish()
        }

        binding.back.setOnClickListener {
            finish()
        }

    }

    fun deletePlant(){
        model.deletePlant(plant.id)
        Toast.makeText(this,"Suppression effectue avec success",Toast.LENGTH_LONG).show()
    }

}