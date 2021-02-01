package com.codecool.wimmexpensetracker.mvvm.repositories

import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Category
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddCategoryActivityRepositoryImplementation() : AddCategoryActivityRepository{

    override fun addCategory (category : Category){
        GlobalScope.launch {
            AppDatabase.getDatabase(null)
                    ?.CategoryDao()?.insertCategory(category)
        }
    }

}