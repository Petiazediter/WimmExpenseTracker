package com.codecool.wimmexpensetracker.data

import android.content.Context
import android.content.SharedPreferences
import com.codecool.wimmexpensetracker.R

class SharedPreferenceController {
    companion object{
        private var sharedPreferences: SharedPreferences? = null

        fun setUpSharedPreferences(appContext : Context){
            if ( sharedPreferences == null) {
                sharedPreferences = appContext.getSharedPreferences(
                    appContext.resources.getString(R.string.shared_preference_name),
                    Context.MODE_PRIVATE
                )
            }
        }

        fun getBudget() : Float = if ( sharedPreferences != null) sharedPreferences!!.getFloat("budget", 50f) else 50f
    }
}