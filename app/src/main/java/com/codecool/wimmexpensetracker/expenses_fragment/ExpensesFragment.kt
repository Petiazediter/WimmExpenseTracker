package com.codecool.wimmexpensetracker.expenses_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.new_expense_activity.NewExpenseActivity
import com.codecool.wimmexpensetracker.product_activity.ActivityButtonListener
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor


class ExpensesFragment : Fragment(), ActivityButtonListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expenses, container, false)
    }

    override fun onResume() {
        super.onResume()
        if ( activity is MainActivityContractor){
            (activity as MainActivityContractor).apply {
                    setMenuTitle(resources.getString(R.string.expenses_fragment_title))
                    setSubMenuTitle(resources.getString(R.string.expenses_fragment_subtitle))
            }
        }
    }

    override fun onButtonPressed() {
        val intent = Intent(context, NewExpenseActivity::class.java)
        startActivity(intent)
    }

}