package com.codecool.wimmexpensetracker.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense (
        @PrimaryKey val uid : String,
        val year : Int,
        val month : Int,
        val day : Int,
        val amount : Float,
        val expenseCategory : String
)