package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.RecyclerAdapterRepository
import com.codecool.wimmexpensetracker.room_db.Category
import com.codecool.wimmexpensetracker.room_db.Expense

class RecyclerAdapterViewModel(private val repo : RecyclerAdapterRepository) : ViewModel() {

    fun getExpensesByCategory( lifecycleOwner: LifecycleOwner,category : Category) : MutableLiveData<List<Expense>>? = repo.getExpensesByCategoryId(lifecycleOwner,category.uId)

}