package com.codecool.wimmexpensetracker.data

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class LocalDatas() {

    var monthlyWage : MutableLiveData<Float> = MutableLiveData(0f)
    var monthlySave : MutableLiveData<Float> = MutableLiveData(5f)

    constructor(context: Context, lifecycleOwner: LifecycleOwner) {
        monthlyWage.value = getSharedPreferences(context).getFloat(SHARED_PREF_WAGE_NAME, 500f)
        monthlySave.value = getSharedPreferences(context).getFloat(SHARED_PREF_SAVE_NAME,5f)

        monthlyWage.observe( lifecycleOwner,{
            getSharedPreferences(context).edit()
                .putFloat(SHARED_PREF_WAGE_NAME,it)
                .apply()
        })

        monthlySave.observe(lifecycleOwner,{
            getSharedPreferences(context).edit()
                .putFloat(SHARED_PREF_SAVE_NAME,it)
                .apply()
        })
    }

    companion object {
        private const val SHARED_PREF_PCKG_NAME = "com.codecool.wimmexpensetracker.sharedpref"
        private const val SHARED_PREF_WAGE_NAME = "com.codecool.wimmexpensetracker.sharedpref.wage"
        private const val SHARED_PREF_SAVE_NAME = "com.codecool.wimmexpensetracker.sharedpref.save"

        private fun getSharedPreferences(context : Context) : SharedPreferences{
            return context.getSharedPreferences(SHARED_PREF_PCKG_NAME, Context.MODE_PRIVATE)
        }

        private var instance : LocalDatas? = null

        fun getInstance(context: Context, lifecycleOwner: LifecycleOwner) : LocalDatas{
            if ( instance == null)
            {
                instance = LocalDatas(context,lifecycleOwner)
            }
            return instance!!
        }
    }
}