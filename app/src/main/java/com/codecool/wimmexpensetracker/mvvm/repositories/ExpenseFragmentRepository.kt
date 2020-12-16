package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Expense

class ExpenseFragmentRepository(val lifecycleOwner: LifecycleOwner) {

    fun getAllExpenses() : MutableLiveData<List<Expense>> {
        val liveData = MutableLiveData<List<Expense>>()

        val expenses : LiveData<List<Expense>>? = AppDatabase.getDatabase(null)
            ?.ExpenseDao()?.getAllExpenses();

        expenses?.observe( lifecycleOwner , {
            liveData.value = it;
        })

        return liveData;
    }

    companion object{
        private var instance : ExpenseFragmentRepository? = null
        fun getInstance(lifecycleOwner: LifecycleOwner) : ExpenseFragmentRepository? {
            if ( instance == null){
                instance = ExpenseFragmentRepository(lifecycleOwner)
            }
            return instance
        }
    }

}