package com.codecool.wimmexpensetracker.expenses_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.adapters.ExpenseAdapter
import com.codecool.wimmexpensetracker.mvvm.view_models.CategoriesViewModel
import com.codecool.wimmexpensetracker.mvvm.view_models.ExpenseFragmentViewModel
import com.codecool.wimmexpensetracker.new_expense_activity.NewExpenseActivity
import com.codecool.wimmexpensetracker.product_activity.ActivityButtonListener
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor
import com.codecool.wimmexpensetracker.room_db.Category
import java.time.LocalDateTime


class ExpensesFragment : Fragment(), ActivityButtonListener {

    private var todayExpensesRecyclerView : RecyclerView? = null
    private var pastExpensesRecyclerView : RecyclerView? = null
    private lateinit var expensesViewModel : ExpenseFragmentViewModel
    private lateinit var categoriesViewModel: CategoriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        expensesViewModel = ViewModelProvider(this).get(ExpenseFragmentViewModel::class.java)
        categoriesViewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        expensesViewModel.init(viewLifecycleOwner)
        categoriesViewModel.init(viewLifecycleOwner)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        createAdapter()
    }


    private fun createAdapter(){
        val categories = categoriesViewModel.allCategories

        context?.let{
            val pastAdapter = ExpenseAdapter(layoutInflater,it, listOf(), listOf())
            val todayAdapter = ExpenseAdapter(layoutInflater,it, listOf(), listOf())

            todayExpensesRecyclerView?.adapter = todayAdapter
            todayExpensesRecyclerView?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            pastExpensesRecyclerView?.adapter = pastAdapter
            pastExpensesRecyclerView?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

            categories?.observe(viewLifecycleOwner, { list ->
                if (list.isNotEmpty()) {
                    pastAdapter.categories = list
                    todayAdapter.categories = list
                    loadExpenses(pastAdapter, todayAdapter)
                }
            })
        }
    }

    private fun loadExpenses(pastAdapter : ExpenseAdapter, todayAdapter : ExpenseAdapter){
        val expenses = expensesViewModel.allExpenses
        expenses?.observe(viewLifecycleOwner,{
            Toast.makeText(context, "Expenses : ${it.size}",Toast.LENGTH_SHORT).show()
            val partition = it.partition { expense ->
                expense.year == LocalDateTime.now().year &&
                        expense.month == LocalDateTime.now().monthValue &&
                        expense.day == LocalDateTime.now().dayOfMonth
            }

            todayAdapter.items = partition.first
            pastAdapter.items = partition.second

            todayAdapter.notifyDataSetChanged()
            pastAdapter.notifyDataSetChanged()
        })
    }

    private fun bindViews(){
        todayExpensesRecyclerView = view?.findViewById(R.id.today_recycler)
        pastExpensesRecyclerView = view?.findViewById(R.id.past_recycler)
    }
}