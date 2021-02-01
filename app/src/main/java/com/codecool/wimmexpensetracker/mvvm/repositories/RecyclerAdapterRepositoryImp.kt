package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Expense

class RecyclerAdapterRepositoryImp : RecyclerAdapterRepository {

    override fun getExpensesByCategoryId ( lifecycleOwner: LifecycleOwner,uuId : String) : MutableLiveData<List<Expense>>{
        val data = MutableLiveData<List<Expense>>()
        AppDatabase.getDatabase(null)
                ?.ExpenseDao()?.getExpenseByCategory(uuId)?.observe(lifecycleOwner,{
                    data.value = it
                })
        return data
    }

}