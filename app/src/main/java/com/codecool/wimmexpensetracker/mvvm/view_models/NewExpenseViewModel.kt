package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.NewExpenseRepository
import com.codecool.wimmexpensetracker.room_db.Expense

class NewExpenseViewModel(val repo : NewExpenseRepository) : ViewModel() {
    fun addExpense(expense : Expense){
        repo.addExpense(expense)
    }
}