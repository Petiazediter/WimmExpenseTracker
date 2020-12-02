package com.codecool.wimmexpensetracker.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor
import com.codecool.wimmexpensetracker.mvvm.view_models.HomeFragmentViewModel

class HomeFragment : Fragment() {

    private var remainingMoney : TextView? = null
    private var remainingBudget : TextView? = null
    private var remainingBudgetDate : TextView? = null

    private var dailyTotal : TextView? = null
    private var dailyTotalSub : TextView? = null

    private var dailyBudget : TextView? = null
    private var dailyBudgetSub : TextView? = null

    lateinit var viewModel : HomeFragmentViewModel
    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        viewModel.init()
        viewModel.getUserExpenses().observe(this, {

        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setUpTexts()
        remainingMoney?.text = "$50"
    }

    private fun setUpTexts(){

    }

    private fun bindViews(view: View){
        remainingMoney = view.findViewById(R.id.remaining_money)
        remainingBudget = view.findViewById(R.id.remaining_budget)
        remainingBudgetDate = view.findViewById(R.id.date_tv)

        dailyTotal = view.findViewById(R.id.daily_total)
        dailyTotalSub = view.findViewById(R.id.daily_total_sub)

        dailyBudget = view.findViewById(R.id.daily_budget)
        dailyBudgetSub = view.findViewById(R.id.daily_budget_sub)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivityContractor).let{
            it.setMenuTitle(resources.getString(R.string.home_fragment_title))
            it.setSubMenuTitle(resources.getString(R.string.settings_fragment_title))
        }
        pairAnimation(remainingMoney,listOf(remainingBudget,remainingBudgetDate))
        pairAnimation(dailyTotal,listOf(dailyTotalSub))
        pairAnimation(dailyBudget, listOf(dailyBudgetSub))

    }

    override fun onPause() {
        super.onPause()
        remainingMoney?.alpha = 0f
        remainingBudget?.alpha = 0f
        remainingBudgetDate?.alpha = 0f
        dailyTotal?.alpha = 0f
        dailyTotalSub?.alpha = 0f
        dailyBudget?.alpha = 0f
        dailyBudgetSub?.alpha = 0f
    }

    private fun pairAnimation (parentTv : TextView?, subTv : List<TextView?>){
        val animation = AnimationUtils.loadAnimation(context,R.anim.text_animations)
        for ( v in subTv) {v?.alpha = 0f}
        animation.startOffset = 0
        animation.duration = 500


        parentTv?.startAnimation(animation)
        parentTv?.alpha = 1f
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                val anim = AnimationUtils.loadAnimation(context,R.anim.text_animations)
                anim.duration = 500
                anim.startOffset = 0
                for ( v in subTv) {
                    v?.startAnimation(anim)
                    v?.alpha = 1f
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
    }

}