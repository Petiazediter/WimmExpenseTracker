package com.codecool.wimmexpensetracker.room_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories() : List<Category>

    @Insert
    fun insertCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)
}