package com.codecool.wimmexpensetracker.room_db

import androidx.room.Dao
import androidx.room.Query
import java.util.*

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses WHERE year = :year AND month = :month AND day = :day")
    fun getExpensesByDate(year: Int, month : Int, day : Int) : List<Expense>


}