package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.Expense

interface HomeFragmentRepository {
    fun getExpenses(lifecycleOwner: LifecycleOwner) : MutableLiveData<List<Expense>>
    fun getCurrentMonthExpenses(lifecycleOwner: LifecycleOwner) : MutableLiveData<List<Expense>>
    fun getAllExpenses(lifecycleOwner: LifecycleOwner) : MutableLiveData<List<Expense>>
    fun getLastFiveMonthExpenses(lifecycleOwner: LifecycleOwner): MutableLiveData<List<HomeFragmentRepositoryImp.DatedExpense>>
}