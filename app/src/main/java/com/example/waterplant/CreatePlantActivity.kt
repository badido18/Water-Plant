package com.example.waterplant

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.waterplant.databinding.ActivityCreatePlantBinding
import com.example.waterplant.room.PlantItem
import com.example.waterplant.viewmodels.MainViewModel
import java.lang.Math.floor
import java.text.SimpleDateFormat
import java.util.*

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

        binding.freqSeek.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
                binding.freqText.text = "Chaque ${floor(seek.progress*30/100.toDouble()).toInt().toString()} jours"
            }
            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }
            override fun onStopTrackingTouch(seek: SeekBar) {

            }
        })
        
        binding.freqNutriSeek.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed

                binding.freqNutriText.text = "Chaque ${floor(seek.progress*30/100.toDouble()).toInt().toString()} jours"
            }
            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }
            override fun onStopTrackingTouch(seek: SeekBar) {

            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun ajouterPlant(){
        val nom = binding.nom.text.toString()
        val latin = binding.nomLatin.text.toString()
        var freqArrosage = floor(binding.freqSeek.progress*30/100.toDouble()).toInt()
        var freqNutriment = floor(binding.freqNutriSeek.progress*30/100.toDouble()).toInt()

        if (freqArrosage == 0)
            freqArrosage = 10000000

        if (freqNutriment == 0)
            freqNutriment= 10000000

        val pattern = "yyyy-MM-dd";
        val simpleDateFormat = SimpleDateFormat(pattern);
        val date = simpleDateFormat.format(Date());

        if(isEmpty(nom) || isEmpty(latin)){
            AlertDialog.Builder(this)
                .setMessage("Veuillez remplir tout les champs svp").setCancelable(true)
                .show()
        }else{
            model.addPlant(PlantItem(nom,latin,freqArrosage,date,freqNutriment,date))
        }


    }
}