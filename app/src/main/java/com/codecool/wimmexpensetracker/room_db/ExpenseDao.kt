package com.codecool.wimmexpensetracker.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses WHERE year = :year AND month = :month AND day = :day")
    fun getExpensesByDate(year: Int, month : Int, day : Int)
            : LiveData<List<Expense>>


    @Insert fun insertExpense(expense: Expense)
}