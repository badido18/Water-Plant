package com.example.waterplant.entities
import androidx.room.Entity

import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val  id: Long = 0,
    var name: String,
    var latinName: String,
    var freqArosage: Int,
    var lastArosage: String,
    var freqNutriment: Int,
    var lastNutriment: String,
//    var photo: String  // pour apres
):Serializable

