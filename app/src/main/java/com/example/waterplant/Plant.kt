package fr.irif.zielonka.exam2022session1gra
import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val  id: Long = 0,
    var name: String,
    var latinName: String,
    var freqArosage: String,
    var freqNutriment: String,
//    var photo: String  // pour apres
)

