package com.codecool.wimmexpensetracker.room_db

import androidx.room.Dao
import androidx.room.Query
import java.util.*

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses WHERE date = :queryDate")
    fun getExpensesByDate(queryDate: Date) : List<Expense>


}