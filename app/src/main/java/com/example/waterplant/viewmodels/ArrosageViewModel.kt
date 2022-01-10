package com.example.waterplant.viewmodels

import android.app.Application
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.waterplant.entities.Plant
import com.example.waterplant.room.PlantItem
import com.example.waterplant.room.BDPlants
import com.example.waterplant.room.IdPlant
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import kotlin.math.log

class ArrosageViewModel(application: Application) : AndroidViewModel(application) {
    val dao = BDPlants.getDatabase(application).monDao()
    var allArrosages : LiveData<List<Plant>> = dao.getPlantsLive()

    fun updatePlant(plant: Plant){
        Log.d("ZOZO","updatePlant $plant" )
        Thread {
            dao.updatePlant(plant)
            allArrosages = dao.getPlantsLive()
        }.start()
    }
//
//    fun deletePlant(id : Long){
//        Thread {
//            dao.deletePlant(IdPlant(id))
//            loadArrosages()
//        }.start()
//    }

}