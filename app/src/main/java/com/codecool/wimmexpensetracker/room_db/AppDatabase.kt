package com.codecool.wimmexpensetracker.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Category::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun CategoryDao() : CategoryDao

    companion object {
        private var instance : AppDatabase? = null
        fun getDatabase(context: Context?) : AppDatabase?{
            if ( instance == null) {
                context?.let{
                    instance = Room.databaseBuilder(it,AppDatabase::class.java, "database-name").build()
                }
            }
            return instance
        }
    }
}