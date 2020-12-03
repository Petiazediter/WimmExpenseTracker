package com.codecool.wimmexpensetracker.mvvm.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Expense
import java.time.LocalDateTime

class HomeFragmentRepository(val lifecycleOwner: LifecycleOwner){
    private lateinit var dataSet : List<Expense>
    private var currentMonthExpenses : List<Expense> = ArrayList()
    private var allExpenses : List<Expense> = ArrayList()
    private var lastMonthDataset : HashMap<Int,List<Expense>> = HashMap<Int,List<Expense>>()


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

    fun getCurrentMonthExpenses() : MutableLiveData<List<Expense>>{
        val data = MutableLiveData<List<Expense>>()

        AppDatabase.getDatabase(null)?.let{ appDatabase ->
            appDatabase.ExpenseDao().getExpensesByMonth(LocalDateTime.now().year, LocalDateTime.now().monthValue).observe(lifecycleOwner, {
                data.value = it
                currentMonthExpenses = it
            })
        }

        return data
    }

    fun getAllExpenses() : MutableLiveData<List<Expense>>{
        val data = MutableLiveData<List<Expense>>()

        AppDatabase.getDatabase(null)?.let{ appDatabase ->
            appDatabase.ExpenseDao().getAllExpenses().observe(lifecycleOwner, {
                data.value = it
                allExpenses = it
            })
        }

        return data
    }

    fun getLastFiveMonthExpenses(): MutableLiveData<HashMap<Int,List<Expense>>>? {
        val data = MutableLiveData<HashMap<Int,List<Expense>>>()
        data.value = hashMapOf()

        val dateNow = Pair(LocalDateTime.now().year, LocalDateTime.now().monthValue)

        val searchedMonts = ArrayList<Pair<Int,Int>>()
        for ( i in 0 until 5){
            searchedMonts.add(
                    Pair( if ( dateNow.second - i > 0) dateNow.first else dateNow.first - 1, if ( dateNow.second - i > 0) (dateNow.second - i) else 13 - i ))
        }

        for ( value in searchedMonts) {
            AppDatabase.getDatabase(null)?.let { appDatabase ->
                appDatabase.ExpenseDao().getExpensesByMonth(value.first,value.second).observe(lifecycleOwner, {
                    lastMonthDataset[value.second] = it
                    data.value = lastMonthDataset
                })
            }
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