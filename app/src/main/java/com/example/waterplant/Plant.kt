package com.example.waterplant
import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val  id: Long = 0,
    var name: String,
    var latinName: String,
    var freqArosage: String,
    var nextArosage: String,
    var freqNutriment: String,
    var lastNutriment: String,
//    var photo: String  // pour apres
)

