package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Expense

class RecyclerAdapterRepository ( val lifecycleOwner: LifecycleOwner) {

    fun getExpensesByCategoryId ( uuId : String) : MutableLiveData<List<Expense>>{
        val data = MutableLiveData<List<Expense>>()
        AppDatabase.getDatabase(null)
                ?.ExpenseDao()?.getExpenseByCategory(uuId)?.observe(lifecycleOwner,{
                    data.value = it
                })
        return data
    }

    companion object{
        private var instance : RecyclerAdapterRepository? = null
        fun getInstance ( lifecycleOwner: LifecycleOwner ) : RecyclerAdapterRepository? {
            if ( instance == null){
                instance = RecyclerAdapterRepository(lifecycleOwner)
            }
            return instance
        }
    }
}