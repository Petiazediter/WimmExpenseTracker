package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.Expense

interface RecyclerAdapterRepository {
    fun getExpensesByCategoryId (lifecycleOwner: LifecycleOwner, uuId : String) : MutableLiveData<List<Expense>>
}