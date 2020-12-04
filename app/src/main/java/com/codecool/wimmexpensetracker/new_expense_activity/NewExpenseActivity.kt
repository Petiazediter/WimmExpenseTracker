package com.codecool.wimmexpensetracker.new_expense_activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.adapters.NewExpenseRecyclerAdapter
import com.codecool.wimmexpensetracker.adapters.NewExpensesRecyclerContractor
import com.codecool.wimmexpensetracker.mvvm.view_models.CategoriesViewModel
import com.codecool.wimmexpensetracker.room_db.Category

class NewExpenseActivity : AppCompatActivity(), NewExpensesRecyclerContractor {

    private var nameEditText : EditText? = null
    private var descriptionEditText : EditText? = null
    private var amountEditText : EditText? = null

    private var categoryRecyclerView : RecyclerView? = null
    private var submitButton : Button? = null
    private var cancelButton : Button? = null

    private lateinit var viewModel : CategoriesViewModel
    private lateinit var recyclerAdapter: NewExpenseRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_expense)

        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        viewModel.init(this)
        bindViews()
        setUpRecycler()
        setRecyclerData()
    }

    private fun setRecyclerData(){
        viewModel.allCategories?.observe(this,{
            recyclerAdapter.list = it
            recyclerAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpRecycler(){
        recyclerAdapter = NewExpenseRecyclerAdapter(applicationContext,
            layoutInflater,listOf(),this)

        categoryRecyclerView?.adapter = recyclerAdapter
        categoryRecyclerView?.layoutManager = LinearLayoutManager(
            applicationContext,LinearLayoutManager.VERTICAL,
            false)
    }

    override fun onCategoryClicked(category: Category) {

    }

    private fun bindViews(){
        nameEditText = findViewById(R.id.title_et)
        descriptionEditText = findViewById(R.id.desc_et)
        amountEditText = findViewById(R.id.amount_et)

        categoryRecyclerView = findViewById(R.id.category_recycler)
        submitButton = findViewById(R.id.button_finish)
        cancelButton = findViewById(R.id.button_cancel)
    }


}