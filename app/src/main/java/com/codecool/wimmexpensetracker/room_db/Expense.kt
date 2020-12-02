package com.codecool.wimmexpensetracker.room_db

import androidx.room.Entity
import java.util.*

@Entity(tableName = "expenses")
data class Expense (
        val uid : String,
        val date : Date,
        val amount : Float,
        val expenseCategory : String
)