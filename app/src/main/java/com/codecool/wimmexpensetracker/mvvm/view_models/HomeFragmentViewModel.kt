package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.HomeFragmentRepository
import com.codecool.wimmexpensetracker.room_db.Expense

class HomeFragmentViewModel : ViewModel() {

    private lateinit var mHomeFragmentRepository : HomeFragmentRepository
    var userExpenses : MutableLiveData<List<Expense>>? = null
    var lastMonthExpenses : MutableLiveData<HashMap<Int,List<Expense>>>? = null

    fun init(lifecycleOwner: LifecycleOwner){
        if  (userExpenses != null){
            return
        }
        mHomeFragmentRepository = HomeFragmentRepository.getInstance(lifecycleOwner)!!
        userExpenses = mHomeFragmentRepository.getExpenses()
        lastMonthExpenses = mHomeFragmentRepository.getLastFiveMonthExpenses()
    }

}