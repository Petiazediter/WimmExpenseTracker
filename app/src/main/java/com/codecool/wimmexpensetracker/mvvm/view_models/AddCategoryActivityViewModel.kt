package com.codecool.wimmexpensetracker.mvvm.view_models

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.AddCategoryActivityRepository
import com.codecool.wimmexpensetracker.room_db.Category

class AddCategoryActivityViewModel : ViewModel() {

    private var mAddCategoryActivityRepository: AddCategoryActivityRepository? = null

    fun init( lifecycleOwner: LifecycleOwner){
        if ( mAddCategoryActivityRepository == null){
            mAddCategoryActivityRepository = AddCategoryActivityRepository.getInstance(lifecycleOwner)
        }
    }

    fun addCategory ( category : Category){
        mAddCategoryActivityRepository?.addCategory(category)
    }
}