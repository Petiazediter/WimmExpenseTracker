package com.codecool.wimmexpensetracker.home_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.mvvm.repositories.HomeFragmentRepositoryImp
import com.codecool.wimmexpensetracker.mvvm.view_models.HomeFragmentViewModel
import com.codecool.wimmexpensetracker.product_activity.ActivityButtonListener
import com.codecool.wimmexpensetracker.product_activity.MainActivity
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor
import com.codecool.wimmexpensetracker.room_db.Expense
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import org.koin.android.viewmodel.ext.android.viewModel
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.Month


class HomeFragment : Fragment(), ActivityButtonListener {

    companion object {
        fun Float.formatTo2Decimals(): String {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            return df.format(this).toString()
        }
    }

    private val colors = listOf(
        R.color.color_lightBlue,
        R.color.color_lightGreen,
        R.color.color_lightRed,
        R.color.color_lightYellow,
        R.color.color_lightPink
    )

    private var remainingMoney: TextView? = null
    private var remainingBudget: TextView? = null
    private var remainingBudgetDate: TextView? = null

    private var dailyTotal: TextView? = null
    private var dailyTotalSub: TextView? = null

    private var dailyBudget: TextView? = null
    private var dailyBudgetSub: TextView? = null
    private var monthDateTV: TextView? = null

    private var allExpenses: TextView? = null
    private var totalExpense: TextView? = null
    private var monthlyAverage: TextView? = null

    private val viewModel: HomeFragmentViewModel by viewModel()
    lateinit var anyChartView: BarChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
    }

    private fun bindViews(view: View) {
        anyChartView = view.findViewById(R.id.any_chart_view)

        remainingMoney = view.findViewById(R.id.remaining_money)
        remainingBudget = view.findViewById(R.id.remaining_budget)
        remainingBudgetDate = view.findViewById(R.id.date_tv)

        dailyTotal = view.findViewById(R.id.daily_total)
        dailyTotalSub = view.findViewById(R.id.daily_total_sub)

        dailyBudget = view.findViewById(R.id.daily_budget)
        dailyBudgetSub = view.findViewById(R.id.daily_budget_sub)

        monthDateTV = view.findViewById(R.id.date_month_tv)
        allExpenses = view.findViewById(R.id.all_expenses)
        monthlyAverage = view.findViewById(R.id.monthly_average)
        totalExpense = view.findViewById(R.id.total_expense)
    }

    override fun onResume() {
        super.onResume()
        setPageTitle()
        startAnimations()
        setUpTexts()
    }

    private fun startAnimations() {
        pairAnimation(remainingMoney, listOf(remainingBudget, remainingBudgetDate))
        pairAnimation(dailyTotal, listOf(dailyTotalSub))
        pairAnimation(dailyBudget, listOf(dailyBudgetSub))
        pairAnimation(monthDateTV, listOf(allExpenses, monthlyAverage, totalExpense))
    }

    @SuppressLint("SetTextI18n")
    private fun setUpTexts() {

        remainingBudgetDate?.text =
            "${LocalDateTime.now().month.name}, ${LocalDateTime.now().dayOfMonth}, ${LocalDateTime.now().year}"

        viewModel.getUserExpenses(viewLifecycleOwner).observe(viewLifecycleOwner, {
            processTexts(
                (MainActivity.localDatas.monthlyWage.value!! - MainActivity.localDatas.monthlySave.value!!) / 30,
                it.map { expense -> expense.amount }.sum(),
                it
            )
        })

        viewModel.getLastMonthExpenses(viewLifecycleOwner).observe(viewLifecycleOwner, {
            setUpChart(it)
        })

        viewModel.getCurrentMonthExpense(viewLifecycleOwner)
            .observe(viewLifecycleOwner, { expenses ->
                allExpenses?.text = "${expenses.size} " + resources.getString(R.string.expenses)
                totalExpense?.text = resources.getString(R.string.monthly_total) + "$${
                    expenses.map { it.amount }.sum().formatTo2Decimals()
                }"
            })

        viewModel.getAllExpenses(viewLifecycleOwner).observe(viewLifecycleOwner, { expenses ->
            monthlyAverage?.text = resources.getString(R.string.monthly_average) + "$${
                if (expenses.size > 0)
                // This is wrong! We need to get the average monthly
                    expenses.groupBy { "${it.year} && ${it.month}" }
                        .map { it.value.sumByDouble { expense -> expense.amount.toDouble() } }
                        .average().toFloat().formatTo2Decimals()
                else 0
            }"
        })
    }

    private fun setUpChart(list: List<HomeFragmentRepositoryImp.DatedExpense>) {
        anyChartView.setDrawValueAboveBar(true)
        anyChartView.setMaxVisibleValueCount(5)
        anyChartView.description.isEnabled = false
        anyChartView.xAxis.isEnabled = false
        val yAxis = anyChartView.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.setDrawGridLines(false)
        yAxis.spaceTop = 15f
        anyChartView.axisRight.isEnabled = false
        val barDataSetList = ArrayList<IBarDataSet>()

        list.sortedBy { it.id }.forEachIndexed { index, item ->
            val barEntryList = ArrayList<BarEntry>()
            barEntryList.add(BarEntry(index.toFloat(), item.expenses.map { it.amount }.sum()))
            val barDataSet =
                BarDataSet(barEntryList, Month.of(item.id.toString().substring(4).toInt()).name)
            barDataSetList.add(barDataSet)
            context?.let {
                barDataSet.color = ContextCompat.getColor(it, colors[index])
            }
        }
        val barData = BarData(barDataSetList)
        anyChartView.data = barData
        anyChartView.invalidate()
    }

    @SuppressLint("SetTextI18n")
    private fun processTexts(budget: Float, expenseSum: Float, list: List<Expense>) {
        monthDateTV?.text = "${LocalDateTime.now().month.name},${LocalDateTime.now().year}"
        allExpenses?.text = "${list.size} " + resources.getString(R.string.expenses)
        dailyBudget?.text = "$${budget.formatTo2Decimals()}"
        dailyTotal?.text = "$${expenseSum}"
        val remainingMon = (budget - expenseSum)
        remainingMoney?.text = "$${remainingMon.formatTo2Decimals()}"
        if (remainingMon < 0) {
            remainingMoney?.setTextColor(resources.getColor(R.color.color_lightRed, context?.theme))
        }
    }

    private fun setPageTitle() {
        // Page title setter
        if (activity is MainActivityContractor) {
            (activity as MainActivityContractor).let {
                it.setMenuTitle(resources.getString(R.string.home_fragment_title))
                it.setSubMenuTitle(resources.getString(R.string.settings_fragment_title))
            }
        }
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

        allExpenses?.alpha = 0f
        monthlyAverage?.alpha = 0f
        monthDateTV?.alpha = 0f
        totalExpense?.alpha = 0f
    }

    private fun pairAnimation(parentTv: TextView?, subTv: List<TextView?>) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.text_animations)
        for (v in subTv) {
            v?.alpha = 0f
        }
        animation.startOffset = 0
        animation.duration = 500


        parentTv?.startAnimation(animation)
        parentTv?.alpha = 1f
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                val anim = AnimationUtils.loadAnimation(context, R.anim.text_animations)
                anim.duration = 500
                anim.startOffset = 0
                for (v in subTv) {
                    v?.startAnimation(anim)
                    v?.alpha = 1f
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
    }


    override fun onButtonPressed() {
        if (activity is MainActivityContractor){
            (activity as MainActivityContractor).changeToFragment(MainActivity.SETTINGS_FRAGMENT_PAGE)
        }
    }
}