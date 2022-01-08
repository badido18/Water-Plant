package com.example.waterplant.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.waterplant.room.MonDao
import com.example.waterplant.entities.Plant

@Database(entities=[Plant::class], version = 3)
abstract class BDPlants : RoomDatabase() {

    abstract fun monDao(): MonDao

    companion object {

        private var instance: BDPlants? = null

        @Synchronized
        fun getDatabase( context : Context): BDPlants {
            if( instance != null )
                return instance!!
            val db = Room.databaseBuilder( context.applicationContext,  BDPlants::class.java , "Plants")
                .fallbackToDestructiveMigration()
                .build()
            instance = db
            return instance!!
        }
    }
}