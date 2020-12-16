package com.codecool.wimmexpensetracker.settings_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.product_activity.ActivityButtonListener
import com.codecool.wimmexpensetracker.product_activity.MainActivity
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor


class SettingsFragment : Fragment(),ActivityButtonListener {

    private var dailyBudget : EditText? = null
    private var monthlyWageEditText: EditText? = null
    private var monthlySaveEditText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    private fun bindViews(){
        dailyBudget = view?.findViewById(R.id.et_daily_max)

        monthlyWageEditText = view?.findViewById(R.id.et_my_wage)
        monthlyWageEditText?.addTextChangedListener({ charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            if ( monthlyWageEditText!!.text.isNullOrEmpty() ) {
                MainActivity.localDatas.monthlyWage.value = 0f
            }else{
                MainActivity.localDatas.monthlyWage.value = textToFloat(monthlyWageEditText!!.text.toString()).toFloat()
            }
            setUpDailyBudget()
        })

        monthlySaveEditText = view?.findViewById(R.id.et_my_save)
        monthlySaveEditText?.addTextChangedListener({ charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            if ( monthlySaveEditText!!.text.isNullOrEmpty() ) {
                MainActivity.localDatas.monthlySave.value = 0f
            }else{
                MainActivity.localDatas.monthlySave.value = textToFloat(monthlySaveEditText!!.text.toString()).toFloat()
            }
            setUpDailyBudget()
        })
    }

    override fun onResume() {
        super.onResume()
        context?.let{ context ->
            (activity as MainActivityContractor).apply {
                setMenuTitle( context.getString(R.string.settings_fragment_title))
                setSubMenuTitle(context.getString(R.string.home_fragment_title))
            }

            var saveText = textToFloat(MainActivity.localDatas.monthlySave.value.toString())
            var wageText = textToFloat(MainActivity.localDatas.monthlyWage.value.toString())

            monthlySaveEditText?.setText(saveText)
            monthlyWageEditText?.setText(wageText)
            setUpDailyBudget()
        }
    }

    private fun textToFloat(s : String) : String{
        if ( !s.contains('.')) return s + ".0"
        else return s
    }

    private fun setUpDailyBudget(){
        MainActivity.localDatas.monthlyWage.value?.let{ wage->
            MainActivity.localDatas.monthlySave.value?.let { save ->
                dailyBudget?.setText(((wage - save)/12).toString())
            }
        }
    }



    override fun onButtonPressed() {
        // go to home fragment
    }


}