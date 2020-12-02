package com.codecool.wimmexpensetracker.room_db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories() : List<Category>


}