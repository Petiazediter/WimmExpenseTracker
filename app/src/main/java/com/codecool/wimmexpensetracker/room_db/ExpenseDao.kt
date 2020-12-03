package com.codecool.wimmexpensetracker.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses")
    fun getAllExpenses() : LiveData<List<Expense>>

    @Query("SELECT * FROM expenses WHERE year = :year AND month = :month AND day = :day")
    fun getExpensesByDate(year: Int, month : Int, day : Int)
            : LiveData<List<Expense>>

    @Query("SELECT * FROM expenses WHERE year = :year AND month = :month")
    fun getExpensesByMonth(year: Int,month: Int)
        :LiveData<List<Expense>>

    @Insert fun insertExpense(expense: Expense)
}