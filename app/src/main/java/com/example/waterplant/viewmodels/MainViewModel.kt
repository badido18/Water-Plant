package com.example.waterplant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.waterplant.entities.Plant
import com.example.waterplant.room.PlantItem
import com.example.waterplant.room.BDPlants
import com.example.waterplant.room.IdPlant

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val dao = BDPlants.getDatabase(application).monDao()
    var plants : LiveData<List<Plant>> = MutableLiveData()

    fun loadPlants(){
        Thread {
            this.plants = dao.getPlants()

        }.start()
    }

    fun addPlant(plant: PlantItem){
        Thread {
            dao.insererPlant(plant)
            loadPlants()
        }.start()
    }

    fun deletePlant(id : Long){
        Thread {
            dao.deletePlant(IdPlant(id))
            loadPlants()
        }.start()
    }

    fun deleteAllPlants(){
        Thread {
            dao.deleteAllPlants()
            loadPlants()
        }.start()
    }

}