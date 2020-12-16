package com.codecool.wimmexpensetracker.mvvm.repositories

import com.codecool.wimmexpensetracker.room_db.Expense

interface NewExpenseRepository {
    fun addExpense(expense : Expense)
}