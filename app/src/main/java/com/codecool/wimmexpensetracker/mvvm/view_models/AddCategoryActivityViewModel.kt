package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.AddCategoryActivityRepository
import com.codecool.wimmexpensetracker.mvvm.repositories.AddCategoryActivityRepositoryImplementation
import com.codecool.wimmexpensetracker.room_db.Category

class AddCategoryActivityViewModel(val repo : AddCategoryActivityRepository) : ViewModel() {

    /*private var mAddCategoryActivityRepositoryImplementation: AddCategoryActivityRepositoryImplementation? = null

    fun init( lifecycleOwner: LifecycleOwner){
        if ( mAddCategoryActivityRepositoryImplementation == null){
            mAddCategoryActivityRepositoryImplementation = AddCategoryActivityRepositoryImplementation.getInstance(lifecycleOwner)
        }
    }

    fun addCategory ( category : Category){
        mAddCategoryActivityRepositoryImplementation?.addCategory(category)
    } */

    fun addCategory( category: Category ){
        repo.addCategory(category)
    }

}