package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.room.RoomDatabase
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Expense
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewExpenseRepository {

    fun addExpense(expense : Expense){
        GlobalScope.launch {
            AppDatabase.getDatabase(null)
                ?.ExpenseDao()
                ?.insertExpense(expense)
        }
    }

    companion object{
        private var instance : NewExpenseRepository? = null
        fun getInstance() : NewExpenseRepository? {
            if ( instance == null){
                instance = NewExpenseRepository()
            }
            return instance
        }
    }
}