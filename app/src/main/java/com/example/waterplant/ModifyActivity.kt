package com.example.waterplant

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.waterplant.databinding.ActivityModifyPlantBinding
import com.example.waterplant.entities.Plant
import com.example.waterplant.room.PlantItem
import com.example.waterplant.utils.ImageManager
import com.example.waterplant.utils.TimeManager
import com.example.waterplant.utils.TimeManager.Companion.NEVER
import com.example.waterplant.viewmodels.MainViewModel
import java.lang.Math.floor

class ModifyActivity : AppCompatActivity() {

    val binding by lazy { ActivityModifyPlantBinding .inflate( layoutInflater ) }
    private val model  by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}
    private val TM by lazy { TimeManager() }
    private val IM by lazy { ImageManager() }
    private lateinit var plant: Plant
    var nutriSeek = 0
    var freqSeek = 0
    val REQUEST_CODE = 100
    var byteArrImage: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        this.plant =  intent.getSerializableExtra("plant_id") as Plant

        binding.nom.setText(plant.name)
        binding.nomLatin.setText(plant.latinName)

        try {
            binding.imageView4.setImageBitmap(IM.getBitmap(plant.image!!))
        }
        catch (e:Exception){
            Log.d("exception","no iamge - put default")
        }

        if(plant.freqArosage>31) {
            binding.freqText.text = "non specifie"
        }
        else{
            binding.freqText.text = "Chaque ${plant.freqArosage} jours"
            freqSeek = plant.freqArosage
            binding.freqSeek.progress = freqSeek*100/30
        }

        if(plant.freqNutriment> 31) {
            binding.freqNutriText.text = "non specifie"
        }
        else{
            binding.freqNutriText.text = "Chaque ${plant.freqNutriment} jours"
            nutriSeek = plant.freqNutriment
            binding.freqNutriSeek.progress = nutriSeek *100/30
        }

        binding.enregistrer.setOnClickListener {
            updatePlant()
        }

        binding.annuler.setOnClickListener {
            finish()
        }

        binding.freqSeek.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                freqSeek = floor(seek.progress*30/100.toDouble()).toInt()
                binding.freqText.text = "Chaque ${freqSeek} jours"

            }
            override fun onStartTrackingTouch(seek: SeekBar) {
            }
            override fun onStopTrackingTouch(seek: SeekBar) {
                if (freqSeek == 0) freqSeek = NEVER
            }
        })

        binding.freqNutriSeek.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                nutriSeek = floor(seek.progress*30/100.toDouble()).toInt()
                binding.freqNutriText.text = "Chaque ${nutriSeek} jours"
            }
            override fun onStartTrackingTouch(seek: SeekBar) {

            }
            override fun onStopTrackingTouch(seek: SeekBar) {
                if (nutriSeek == 0) nutriSeek = NEVER
            }
        })

        binding.addImageBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            binding.imageView4.setImageURI(data?.data)
            plant.image = IM.getBytes(binding.imageView4)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updatePlant(){
        plant.name = binding.nom.text.toString()
        plant.latinName = binding.nomLatin.text.toString()
        if (nutriSeek == 0) nutriSeek = NEVER
        if (freqSeek == 0) freqSeek = NEVER
        Log.d("FREQ SEKK",freqSeek.toString())
        Log.d("NUTRI SEKK",nutriSeek.toString())
        plant.freqArosage = freqSeek
        plant.freqNutriment = nutriSeek

        if(TextUtils.isEmpty(plant.name) || TextUtils.isEmpty( plant.latinName)){
            AlertDialog.Builder(this)
                .setMessage("Veuillez remplir tout les champs svp").setCancelable(true)
                .show()
        }else{
            model.updatePlant(plant)
            finish()
        }


    }
}