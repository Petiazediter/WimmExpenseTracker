package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.LifecycleOwner
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Category
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddCategoryActivityRepository(val lifecycleOwner: LifecycleOwner) {

    fun addCategory (category : Category){
        GlobalScope.launch {
            AppDatabase.getDatabase(null)
                    ?.CategoryDao()?.insertCategory(category)
        }
    }

    companion object{
        private var instance : AddCategoryActivityRepository? = null

        fun getInstance(lifecycleOwner: LifecycleOwner) : AddCategoryActivityRepository?{
            if ( instance == null ) {
                instance = AddCategoryActivityRepository(lifecycleOwner)
            }
            return instance
        }
    }

}