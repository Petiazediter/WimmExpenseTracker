package com.codecool.wimmexpensetracker.room_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codecool.wimmexpensetracker.data.CategoryColor

@Entity(tableName = "categories")
data class Category (
    @PrimaryKey val uId : String,
    @ColumnInfo(name="category_name") val categoryName : String,
    @ColumnInfo(name="category_color") val colorId : CategoryColor
)
