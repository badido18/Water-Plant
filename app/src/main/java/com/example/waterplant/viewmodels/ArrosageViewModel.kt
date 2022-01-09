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

class ArrosageViewModel(application: Application) : AndroidViewModel(application) {
    val dao = BDPlants.getDatabase(application).monDao()
    val allArrosages : LiveData<List<Plant>> = dao.getPlants()
    val cc = this
//    fun loadArrosages(){
//        Log.d("ArrosageViewModel", "loadArrosages")
//        Thread {
//            this.allArrosages = dao.getPlants()
//        }.start()
//    }

//    fun addPlant(plant: PlantItem){
//        Thread {
//            dao.insererPlant(plant)
//            loadArrosages()
//        }.start()
//    }
//
//    fun deletePlant(id : Long){
//        Thread {
//            dao.deletePlant(IdPlant(id))
//            loadArrosages()
//        }.start()
//    }

}