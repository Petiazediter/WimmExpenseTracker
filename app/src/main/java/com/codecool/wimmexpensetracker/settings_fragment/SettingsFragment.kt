package com.codecool.wimmexpensetracker.settings_fragment


import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.Debug
import android.service.autofill.RegexValidator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentActivity
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.product_activity.ActivityButtonListener
import com.codecool.wimmexpensetracker.product_activity.MainActivity
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor


class SettingsFragment : Fragment(),ActivityButtonListener {

    private var dailyBudget: EditText? = null
    private var monthlyWageEditText: EditText? = null
    private var monthlySaveEditText: EditText? = null
    private var saveButton: Button? = null

    private val floatRegex : Regex = Regex("([0-9]).+([0-9])");

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
        addListeners()
    }

    private fun bindViews(){
        dailyBudget = view?.findViewById(R.id.et_daily_max)
        monthlyWageEditText = view?.findViewById(R.id.et_my_wage)
        monthlySaveEditText = view?.findViewById(R.id.et_my_save)
        saveButton = view?.findViewById(R.id.save_button)
    }

    private fun addListeners(){
        saveButton?.setOnClickListener {

            val monthlyWageText = monthlyWageEditText?.text.toString();
            var wage = createFloatFromText(monthlyWageText,MainActivity.localDatas.monthlyWage.value);

            val monthlySaveText = monthlySaveEditText?.text.toString();
            var save = createFloatFromText(monthlySaveText, MainActivity.localDatas.monthlySave.value);

            MainActivity.localDatas.monthlyWage.value = wage;
            MainActivity.localDatas.monthlySave.value = save;

            Log.d("onClickListener", "Wage : $wage | Save : $save")

            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
            setEditTextsToDefault()
        }
    }

    private fun createFloatFromText( text : String, defaultValue : Float?) : Float?{
        if ( text.isNotBlank() && text.isNotEmpty()){
            if ( floatRegex.matches(text)){
                return text.toFloat()
            } else{
                return (text + ".00").toFloat()
            }
        }
        return defaultValue;
    }

    override fun onResume() {
        super.onResume()
        setMenuTexts(context,activity)
        setEditTextsToDefault();
    }

    private fun setEditTextsToDefault(){
        monthlySaveEditText?.setText( MainActivity.localDatas.monthlySave.value.toString());
        monthlyWageEditText?.setText( MainActivity.localDatas.monthlyWage.value.toString());
        calculateDailyBudget(MainActivity.localDatas.monthlySave.value,MainActivity.localDatas.monthlyWage.value)
    }

    private fun calculateDailyBudget(monthlySave : Float?, monthlyWage : Float?){
        if ( monthlySave != null && monthlyWage != null) {
            var dailySave = (monthlyWage - monthlySave) / 12
            dailyBudget?.setText ( dailySave.toString())
        }
    }

    private fun setMenuTexts(context : Context?,activity : FragmentActivity?){
        context?.let { context ->
            (activity as MainActivityContractor).apply {
                setMenuTitle(context.getString(R.string.settings_fragment_title))
                setSubMenuTitle(context.getString(R.string.home_fragment_title))
            }
        }
    }


    override fun onButtonPressed() {
        // go to home fragment
    }

}