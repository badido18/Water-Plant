package com.example.waterplant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.waterplant.databinding.ActivityCreatePlantBinding
import com.example.waterplant.databinding.ActivityMainBinding
import com.example.waterplant.room.PlantItem
import com.example.waterplant.viewmodels.MainViewModel

class CreatePlantActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCreatePlantBinding .inflate( layoutInflater ) }
    private val model  by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun ajouterPlant(){
        val nom = binding.nom.text.toString()
        val latin = binding.nomLatin.text.toString()

        // add activity yo add arrosage frequence

        if(isEmpty(nom) || isEmpty(latin)){
            AlertDialog.Builder(this)
                .setMessage("Veuillez remplir tout les champs svp").setCancelable(true)
                .show()
        }else{
            model.addPlant(PlantItem(nom,latin,"","","",""))
        }

    }
}