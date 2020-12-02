package com.codecool.wimmexpensetracker.room_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category (
    @PrimaryKey val uId : Int,
    @ColumnInfo(name="category_name") val categoryName : String,
    @ColumnInfo(name="category_color") val colorId : Int
)