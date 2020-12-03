package com.codecool.wimmexpensetracker.mvvm.view_models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.wimmexpensetracker.mvvm.repositories.RecyclerAdapterRepository
import com.codecool.wimmexpensetracker.room_db.Category
import com.codecool.wimmexpensetracker.room_db.Expense

class RecyclerAdapterViewModel : ViewModel() {

    private var mRecyclerAdapterRepository : RecyclerAdapterRepository? = null

    fun init(lifecycleOwner: LifecycleOwner){
        if ( mRecyclerAdapterRepository == null){
            mRecyclerAdapterRepository = RecyclerAdapterRepository.getInstance(lifecycleOwner)
        }
    }

    fun getExpensesByCategory( category : Category) : MutableLiveData<List<Expense>>? = mRecyclerAdapterRepository?.getExpensesByCategoryId(category.uId)

}