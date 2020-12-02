package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.HomeFragmentRepository
import com.codecool.wimmexpensetracker.room_db.Expense

class HomeFragmentViewModel : ViewModel() {

    private val mHomeFragmentRepository : HomeFragmentRepository = HomeFragmentRepository.getInstance()!!
    private var userExpenses : MutableLiveData<List<Expense>> = MutableLiveData<List<Expense>>()

    fun init(){
        if  (userExpenses != null){
            return
        }
        userExpenses = mHomeFragmentRepository.getExpenses()
    }

    fun getUserExpenses() : MutableLiveData<List<Expense>> = userExpenses

}