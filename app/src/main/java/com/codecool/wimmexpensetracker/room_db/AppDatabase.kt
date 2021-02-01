package com.codecool.wimmexpensetracker.room_db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codecool.wimmexpensetracker.data.CategoryColor

@Database(entities = [Category::class,Expense::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun CategoryDao() : CategoryDao
    abstract fun ExpenseDao() : ExpenseDao

    companion object {
        private var instance : AppDatabase? = null
        fun getDatabase(context: Context?) : AppDatabase?{
            if ( instance == null) {
                context?.let{
                    val appDatabase = Room.databaseBuilder(it,AppDatabase::class.java, "database-name")
                        .build()
                    instance = appDatabase
                }
            }

            return instance
        }
    }
}