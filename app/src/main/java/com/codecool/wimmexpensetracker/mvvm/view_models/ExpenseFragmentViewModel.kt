package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.ExpenseFragmentRepository
import com.codecool.wimmexpensetracker.room_db.Expense

class ExpenseFragmentViewModel : ViewModel(){

    private var mExpenseFragmentRepository : ExpenseFragmentRepository? = null
    public var allExpenses : MutableLiveData<List<Expense>>? = null

    fun init(lifecycleOwner: LifecycleOwner){
        if ( mExpenseFragmentRepository == null) {
            mExpenseFragmentRepository = ExpenseFragmentRepository.getInstance(lifecycleOwner)
            allExpenses = mExpenseFragmentRepository?.getAllExpenses()
        }
    }

}