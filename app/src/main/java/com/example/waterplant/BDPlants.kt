package com.example.waterplant

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Plant::class], version = 2)
abstract class BDPlants : RoomDatabase() {

    abstract fun monDao(): MonDao

    companion object {

        private var instance: BDPlants? = null

        @Synchronized
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