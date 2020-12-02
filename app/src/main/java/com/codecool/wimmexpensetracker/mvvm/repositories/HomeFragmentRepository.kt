package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Expense
import java.time.LocalDateTime

class HomeFragmentRepository{
    private lateinit var dataSet : List<Expense>

    fun getExpenses() : MutableLiveData<List<Expense>>{
        val data = MutableLiveData<List<Expense>>()
        data.value = listOf()

        AppDatabase.getDatabase(null)?.let{database ->
            dataSet = database.ExpenseDao().getExpensesByDate(LocalDateTime.now().year,LocalDateTime.now().monthValue,LocalDateTime.now().dayOfMonth)
            data.value = dataSet
        }

        return data
    }

    companion object{
        private var instance : HomeFragmentRepository? = null

        fun getInstance() : HomeFragmentRepository?{
            if ( instance == null ) {
                instance = HomeFragmentRepository()
            }
            return instance
        }


    }
}