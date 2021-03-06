package com.example.waterplant

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
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
import com.example.waterplant.utils.ImageManager
import com.example.waterplant.utils.TimeManager
import com.example.waterplant.utils.TimeManager.Companion.NEVER
import com.example.waterplant.viewmodels.MainViewModel
import java.lang.Math.floor
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.Bitmap as Bitmap1

class CreatePlantActivity : AppCompatActivity() {


    private val binding by lazy { ActivityCreatePlantBinding .inflate( layoutInflater ) }
    private val model  by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}
    private val TM by lazy { TimeManager() }
    private val IM by lazy { ImageManager() }
    private var freqSeek = NEVER
    private var nutriSeek = NEVER
    val REQUEST_CODE = 100
    var byteArrImage: ByteArray? = null

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
            byteArrImage = IM.getBytes(binding.imageView4)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun ajouterPlant(){
        val nom = binding.nom.text.toString()
        val latin = binding.nomLatin.text.toString()
        var freqArrosage = freqSeek
        var freqNutriment = nutriSeek


        val date = TM.todayToString()

        if(isEmpty(nom) || isEmpty(latin)){
            AlertDialog.Builder(this)
                .setMessage("Veuillez remplir tout les champs svp").setCancelable(true)
                .show()
        }else{
            model.addPlant(PlantItem(nom,latin,freqArrosage,date,freqNutriment,date,byteArrImage))
            finish()
        }


    }
}