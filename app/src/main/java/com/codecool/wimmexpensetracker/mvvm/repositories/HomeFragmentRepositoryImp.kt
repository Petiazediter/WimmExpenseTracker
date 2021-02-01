package com.codecool.wimmexpensetracker.mvvm.repositories

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.codecool.wimmexpensetracker.room_db.AppDatabase
import com.codecool.wimmexpensetracker.room_db.Expense
import java.time.LocalDateTime

class HomeFragmentRepositoryImp() : HomeFragmentRepository{
    private lateinit var dataSet : List<Expense>
    private var currentMonthExpenses : List<Expense> = ArrayList()
    private var allExpenses : List<Expense> = ArrayList()
    private var lastMonthDataset = ArrayList<DatedExpense>()


    override fun getExpenses(lifecycleOwner: LifecycleOwner) : MutableLiveData<List<Expense>>{
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

    override fun getCurrentMonthExpenses(lifecycleOwner: LifecycleOwner) : MutableLiveData<List<Expense>>{
        val data = MutableLiveData<List<Expense>>()

        AppDatabase.getDatabase(null)?.let{ appDatabase ->
            appDatabase.ExpenseDao().getExpensesByMonth(LocalDateTime.now().year, LocalDateTime.now().monthValue).observe(lifecycleOwner, {
                data.value = it
                currentMonthExpenses = it
            })
        }

        return data
    }

    //TODO("replace with the other view model. ( expensefragment repo)")
    override fun getAllExpenses(lifecycleOwner: LifecycleOwner) : MutableLiveData<List<Expense>>{
        val data = MutableLiveData<List<Expense>>()

        AppDatabase.getDatabase(null)?.let{ appDatabase ->
            appDatabase.ExpenseDao().getAllExpenses().observe(lifecycleOwner, {
                data.value = it
                allExpenses = it
            })
        }

        return data
    }

    /*override fun getLastFiveMonthExpenses(lifecycleOwner: LifecycleOwner): MutableLiveData<HashMap<Int,List<Expense>>> {
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
    } */

    override fun getLastFiveMonthExpenses(lifecycleOwner: LifecycleOwner): MutableLiveData<List<DatedExpense>> {
        val data = MutableLiveData<List<DatedExpense>>()
        lastMonthDataset = arrayListOf()
        data.value = listOf()

        val dateNow = Pair(LocalDateTime.now().year, LocalDateTime.now().monthValue)
        val searchedMonths = ArrayList<SimpleDate>()
        for ( i in 0 until 5) {
            searchedMonths.add(
                    SimpleDate(if ( dateNow.second - i > 0) dateNow.first else dateNow.first - 1,
                        if ( dateNow.second - i > 0) (dateNow.second - i) else 13 - i ))
        }

        for (value in searchedMonths){
            AppDatabase.getDatabase(null)?.let{ appDatabase ->
                appDatabase.ExpenseDao().getExpensesByMonth(value.year,value.month).observe(lifecycleOwner,{

                    val id = "${value.year}${if (value.month <=9) "0" + value.month else value.month}".toInt()
                    val index = getExpenseById(id)
                    if ( index >= 0) {
                        lastMonthDataset[index].expenses = it;
                    }else {
                        lastMonthDataset.add(
                            DatedExpense(
                                "${value.year}${if (value.month <= 9) "0" + value.month else value.month}".toInt(),
                                it
                            )
                        )
                    }
                    data.value = lastMonthDataset
                })
            }
        }

        return data
    }

    private fun getExpenseById(id : Int) : Int {
        val filteredTable = lastMonthDataset.filter{it.id == id}
        if (filteredTable.isNullOrEmpty()){
            return -1
        }
        return lastMonthDataset.indexOf(filteredTable[0])
    }

    data class DatedExpense(val id : Int, var expenses : List<Expense>)
    data class SimpleDate(val year : Int, val month : Int)
}