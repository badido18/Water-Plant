package com.example.waterplant

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val dao = BDPlants.getDatabase(application).monDao()
    var plants : LiveData<List<Plant>> = MutableLiveData()

    fun loadPlants(){
        Thread {
            this.plants = dao.getPlants()

        }.start()
    }

}