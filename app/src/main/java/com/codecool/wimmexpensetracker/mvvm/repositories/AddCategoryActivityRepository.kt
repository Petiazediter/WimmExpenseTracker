package com.codecool.wimmexpensetracker.mvvm.repositories

import com.codecool.wimmexpensetracker.room_db.Category

interface AddCategoryActivityRepository {
    fun addCategory(category: Category)
}