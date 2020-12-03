package com.codecool.wimmexpensetracker.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories() : LiveData<List<Category>>

    @Insert
    fun insertCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)
}