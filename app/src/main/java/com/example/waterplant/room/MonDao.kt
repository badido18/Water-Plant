package com.example.waterplant.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.waterplant.entities.Plant

data class  PlantItem(
    var name: String,
    var latinName: String,
    var freqArosage: Int,
    var lastArosage: String,
    var freqNutriment: Int,
    var lastNutriment: String,
    var image: ByteArray? = null
)

data class IdPlant(
    val id:Long
)

@Dao
interface MonDao {

    @Insert(entity= Plant::class)
    fun insererPlant(plant : PlantItem) :  Long

    @Query("DELETE FROM Plant")
    fun deleteAllPlants()

    @Delete(entity= Plant::class)
    fun deletePlant(IdPlant : IdPlant)

    @Update
    fun updatePlant(plant: Plant)

    @Query("SELECT * FROM Plant")
    fun getPlants(): List<Plant>

    @Query("SELECT * FROM Plant")
    fun getPlantsLive(): LiveData<List<Plant>>

    @Query("SELECT * FROM Plant where lower(name) LIKE '%' || lower(:nom)  || '%' or lower(latinName) LIKE '%' || lower(:nom)  || '%' ")
    fun loadPartialName(nom: String): List<Plant>

}
