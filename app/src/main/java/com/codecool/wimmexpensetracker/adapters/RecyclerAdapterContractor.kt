package com.codecool.wimmexpensetracker.adapters

import com.codecool.wimmexpensetracker.room_db.Category

interface RecyclerAdapterContractor {
    fun onItemDelteted ( category: Category )
}