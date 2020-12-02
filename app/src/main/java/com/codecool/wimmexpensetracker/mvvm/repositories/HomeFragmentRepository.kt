package com.codecool.wimmexpensetracker.mvvm.repositories

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Expense
import java.time.LocalDateTime

class HomeFragmentRepository(val lifecycleOwner: LifecycleOwner){
    private lateinit var dataSet : List<Expense>


    fun getExpenses() : MutableLiveData<List<Expense>>{
        val data = MutableLiveData<List<Expense>>()
        data.value = listOf()

        AppDatabase.getDatabase(null)?.let{database ->
            database.ExpenseDao().getExpensesByDate(LocalDateTime.now().year,LocalDateTime.now().monthValue,LocalDateTime.now().dayOfMonth).observe(lifecycleOwner,  {
                dataSet = it
                data.value = dataSet
            })
        }

        return data
    }

    companion object{
        private var instance : HomeFragmentRepository? = null

        fun getInstance(lifecycleOwner: LifecycleOwner) : HomeFragmentRepository?{
            if ( instance == null ) {
                instance = HomeFragmentRepository(lifecycleOwner)
            }
            return instance
        }


    }
}