package com.example.waterplant

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.irif.zielonka.exam2022session1gra.Plant

data class  PlantItem(
    var name: String,
    var latinName: String,
    var freqArosage: String,
    var freqNutriment: String,
//    var photo: String  // pour apres
)

data class IdPlant(
    val id:Long
)

@Dao
interface MonDao {
    /*
    @Insert(entity=Plants::class)
    fun insererPlant( auteur: String, titre: String, emplacement: String) :  Long
*/
    @Insert(entity= Plant::class)
    fun insererPlant(plant : PlantItem) :  Long

    @Query("DELETE FROM Plant")
    fun deleteAllPlants()

    @Delete(entity= Plant::class)
    fun deletePlant(IdPlant : IdPlant)

    @Update
    fun updatePlant(plant: Plant)

    @Query("SELECT * FROM Plant")
    fun getPlants(): LiveData<List<Plant>>

    @Query("SELECT * FROM Plant WHERE lower(name) LIKE lower(:pref) || '%'")
    fun getPlantsParName( pref: String ): LiveData<List<Plant>>

    @Query("SELECT * FROM Plant WHERE lower(latinName) LIKE '%' || lower(:t) || '%'")
    fun getPlantsParLatinName( t: String ) : LiveData<List<Plant>>

}