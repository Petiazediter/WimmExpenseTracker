package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Category
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoriesRepositoryImp : CategoriesRepository {

    private lateinit var allCategories : MutableLiveData<List<Category>>

    override fun getCategories(lifecycleOwner: LifecycleOwner) : MutableLiveData<List<Category>>{
        val data = MutableLiveData<List<Category>>()
        AppDatabase.getDatabase(null)
        ?.CategoryDao()
        ?.getAllCategories()?.observe(lifecycleOwner, {
                    data.value = it
                    allCategories = data
                })

        return data
    }

    override fun removeCategory ( category: Category) {
        GlobalScope.launch {
            AppDatabase.getDatabase(null)
                    ?.CategoryDao()
                    ?.deleteCategory(category)
        }
    }
}