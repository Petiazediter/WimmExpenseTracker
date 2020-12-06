package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.NewExpenseRepository
import com.codecool.wimmexpensetracker.room_db.Expense

class NewExpenseViewModel : ViewModel() {

    private var mNewExpenseRepository : NewExpenseRepository? = null

    fun init(){
        mNewExpenseRepository = NewExpenseRepository.getInstance()
    }

    fun addExpense(expense : Expense){
        mNewExpenseRepository?.addExpense(expense)
    }
}