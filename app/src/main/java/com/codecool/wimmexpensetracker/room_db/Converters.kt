package com.codecool.wimmexpensetracker.room_db

import androidx.room.TypeConverter
import com.codecool.wimmexpensetracker.data.CategoryColor

class Converters {
    @TypeConverter
    fun toCategoryColor (value : String) : CategoryColor = CategoryColor.valueOf(value)

    @TypeConverter
    fun fromCategoryColor ( color: CategoryColor ) : String = color.name
}