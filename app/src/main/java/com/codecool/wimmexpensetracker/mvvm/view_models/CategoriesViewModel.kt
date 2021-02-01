package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.CategoriesRepository
import com.codecool.wimmexpensetracker.room_db.Category

class CategoriesViewModel(val repo : CategoriesRepository) : ViewModel() {
    fun deleteCategory(category : Category){
        repo.removeCategory(category)
    }

    fun getAllCategories(lifecycleOwner: LifecycleOwner) : MutableLiveData<List<Category>> {
        return repo.getCategories(lifecycleOwner)
    }
}