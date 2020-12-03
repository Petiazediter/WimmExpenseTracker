package com.codecool.wimmexpensetracker.adapters

import com.codecool.wimmexpensetracker.room_db.Category

interface NewExpensesRecyclerContractor {
    fun onCategoryClicked(category: Category)
}