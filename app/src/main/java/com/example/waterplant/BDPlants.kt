package com.example.waterplant

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.irif.zielonka.exam2022session1gra.Plant

@Database(entities=[Plant::class], version = 2)
abstract class BDPlants : RoomDatabase() {
    abstract fun monDao(): MonDao

    companion object {
        @Volatile
        private var instance: BDPlants? = null

        fun getDatabase( context : Context): BDPlants {
            if( instance != null )
                return instance!!
            val db = Room.databaseBuilder( context.applicationContext,  BDPlants::class.java , "Plants")
                .fallbackToDestructiveMigration() /* bd détruite si on change la version */
                .build()
            instance = db
            return instance!!
        }
    }
}