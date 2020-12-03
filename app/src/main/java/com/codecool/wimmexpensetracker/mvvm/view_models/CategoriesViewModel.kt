package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.CategoriesRepository
import com.codecool.wimmexpensetracker.room_db.Category

class CategoriesViewModel : ViewModel() {

    private var mCategoriesRepository : CategoriesRepository? = null
    private var allCategories : MutableLiveData<List<Category>>? = null

    fun init(lifecycleOwner: LifecycleOwner){
        if ( mCategoriesRepository == null){
            mCategoriesRepository = CategoriesRepository.getInstance(lifecycleOwner)
        }
        allCategories = mCategoriesRepository?.getCategories()
    }

}