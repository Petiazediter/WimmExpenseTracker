package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.Category

interface CategoriesRepository {
    fun getCategories(lifecycleOwner: LifecycleOwner) : MutableLiveData<List<Category>>
    fun removeCategory ( category: Category)
}