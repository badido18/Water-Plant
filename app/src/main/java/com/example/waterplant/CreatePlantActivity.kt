package com.example.waterplant

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.waterplant.databinding.ActivityCreatePlantBinding
import com.example.waterplant.databinding.ActivityMainBinding
import com.example.waterplant.room.PlantItem
import com.example.waterplant.viewmodels.MainViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CreatePlantActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCreatePlantBinding .inflate( layoutInflater ) }
    private val model  by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.enregistrer.setOnClickListener {
            ajouterPlant()
        }

        binding.annuler.setOnClickListener {
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun ajouterPlant(){
        val nom = binding.nom.text.toString()
        val latin = binding.nomLatin.text.toString()

        // add activity yo add arrosage frequence
        val freqArrosage = 0
        val freqNutriment = 0
//        val today = LocalDate.now() as String
        val today = "2022-01-01"

        if(isEmpty(nom) || isEmpty(latin)){
            AlertDialog.Builder(this)
                .setMessage("Veuillez remplir tout les champs svp").setCancelable(true)
                .show()
        }else{
            model.addPlant(PlantItem(nom,latin,freqArrosage,today,freqNutriment,today))
            finish()
        }


    }
}