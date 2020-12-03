package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Category
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoriesRepository ( val lifecycleOwner: LifecycleOwner) {

    private lateinit var allCategories : MutableLiveData<List<Category>>

    fun getCategories() : MutableLiveData<List<Category>>{
        val data = MutableLiveData<List<Category>>()
        AppDatabase.getDatabase(null)
        ?.CategoryDao()
        ?.getAllCategories()?.observe(lifecycleOwner, {
                    data.value = it
                    allCategories = data
                })

        return data
    }

    fun removeCategory ( category: Category) {
        GlobalScope.launch {
            AppDatabase.getDatabase(null)
                    ?.CategoryDao()
                    ?.deleteCategory(category)
        }
    }

    companion object{
        private var instance : CategoriesRepository? = null
        fun getInstance(lifecycleOwner: LifecycleOwner) : CategoriesRepository? {
            if ( instance == null){
                instance = CategoriesRepository(lifecycleOwner)
            }
            return instance
        }
    }

}