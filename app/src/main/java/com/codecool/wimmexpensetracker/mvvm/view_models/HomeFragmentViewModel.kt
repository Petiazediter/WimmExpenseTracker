package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.HomeFragmentRepository
import com.codecool.wimmexpensetracker.mvvm.repositories.HomeFragmentRepositoryImp
import com.codecool.wimmexpensetracker.room_db.Expense

class HomeFragmentViewModel(val repo : HomeFragmentRepository) : ViewModel() {
    
    fun getUserExpenses(lifecycleOwner : LifecycleOwner) : MutableLiveData<List<Expense>>{
        return repo.getExpenses(lifecycleOwner)
    }

    fun getLastMonthExpenses(lifecycleOwner: LifecycleOwner) : MutableLiveData<List<HomeFragmentRepositoryImp.DatedExpense>>{
        return repo.getLastFiveMonthExpenses(lifecycleOwner)
    }

    fun getAllExpenses(lifecycleOwner: LifecycleOwner ) : MutableLiveData<List<Expense>>{
        return repo.getAllExpenses(lifecycleOwner)
    }

    fun getCurrentMonthExpense ( lifecycleOwner: LifecycleOwner ) : MutableLiveData<List<Expense>>{
        return repo.getCurrentMonthExpenses(lifecycleOwner)
    }

}