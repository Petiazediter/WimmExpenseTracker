package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.ExpenseFragmentRepository
import com.codecool.wimmexpensetracker.room_db.Expense

class ExpenseFragmentViewModel(val repo : ExpenseFragmentRepository) : ViewModel(){

    fun getAllExpenses(lifecycleOwner : LifecycleOwner) : MutableLiveData<List<Expense>> {
        return repo.getAllExpenses(lifecycleOwner)
    }

}