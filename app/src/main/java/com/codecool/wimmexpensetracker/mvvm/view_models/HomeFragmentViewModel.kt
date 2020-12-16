package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.HomeFragmentRepository
import com.codecool.wimmexpensetracker.room_db.Expense

class HomeFragmentViewModel(val repo : HomeFragmentRepository) : ViewModel() {

   /* private lateinit var mHomeFragmentRepository : HomeFragmentRepository
    var userExpenses : MutableLiveData<List<Expense>>? = null
    var lastMonthExpenses : MutableLiveData<HashMap<Int,List<Expense>>>? = null
    var allExpenses : MutableLiveData<List<Expense>>? = null
    var currentMonthExpense : MutableLiveData<List<Expense>>? = null

    fun init(lifecycleOwner: LifecycleOwner){
        if  (userExpenses != null){
            return
        }
        mHomeFragmentRepository = HomeFragmentRepository.getInstance(lifecycleOwner)!!
        userExpenses = mHomeFragmentRepository.getExpenses()
        lastMonthExpenses = mHomeFragmentRepository.getLastFiveMonthExpenses()
        allExpenses = mHomeFragmentRepository.getAllExpenses()
        currentMonthExpense = mHomeFragmentRepository.getCurrentMonthExpenses()
    } */

    fun getUserExpenses(lifecycleOwner : LifecycleOwner) : MutableLiveData<List<Expense>>{
        return repo.getExpenses(lifecycleOwner)
    }

    fun getLastMonthExpenses(lifecycleOwner: LifecycleOwner) : MutableLiveData<HashMap<Int,List<Expense>>>{
        return repo.getLastFiveMonthExpenses(lifecycleOwner)
    }

    fun getAllExpenses(lifecycleOwner: LifecycleOwner ) : MutableLiveData<List<Expense>>{
        return repo.getAllExpenses(lifecycleOwner)
    }

    fun getCurrentMonthExpense ( lifecycleOwner: LifecycleOwner ) : MutableLiveData<List<Expense>>{
        return repo.getCurrentMonthExpenses(lifecycleOwner)
    }

}