package com.codecool.wimmexpensetracker.new_expense_activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.adapters.NewExpenseRecyclerAdapter
import com.codecool.wimmexpensetracker.adapters.NewExpensesRecyclerContractor
import com.codecool.wimmexpensetracker.data.CategoryColor
import com.codecool.wimmexpensetracker.mvvm.view_models.CategoriesViewModel
import com.codecool.wimmexpensetracker.room_db.Category

class NewExpenseActivity : AppCompatActivity(), NewExpensesRecyclerContractor {

    private var nameEditText : EditText? = null
    private var amountEditText : EditText? = null

    private var categoryRecyclerView : RecyclerView? = null
    private var submitButton : Button? = null
    private var cancelButton : Button? = null

    private lateinit var viewModel : CategoriesViewModel
    private lateinit var recyclerAdapter: NewExpenseRecyclerAdapter

    private var previewName : TextView? = null
    private var previewCategoryName : TextView? = null
    private var previewColor : ImageView? = null
    private var previewAmount : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_expense)

        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        viewModel.init(this)
        bindViews()
        setUpRecycler()
        setRecyclerData()

        addEditTextFunctions()
        cancelButton?.setOnClickListener { finish() }
    }

    @SuppressLint("SetTextI18n")
    private fun addEditTextFunctions() {
        nameEditText?.addTextChangedListener{
            previewName?.text = it.toString()
        }

        amountEditText?.addTextChangedListener {
            previewAmount?.text = "$${it.toString()}"
        }
    }


    override fun onCategoryClicked(category: Category) {
        val color = when ( category.colorId ){
            CategoryColor.PINK -> R.color.color_lightPink
            CategoryColor.YELLOW -> R.color.color_lightYellow
            CategoryColor.GREEN -> R.color.color_lightGreen
            CategoryColor.RED -> R.color.color_lightRed
            CategoryColor.BLUE -> R.color.color_lightBlue
        }
        previewColor?.setBackgroundColor(applicationContext.getColor(color))
    }

    private fun bindViews(){
        nameEditText = findViewById(R.id.title_et)
        amountEditText = findViewById(R.id.amount_et)

        categoryRecyclerView = findViewById(R.id.category_recycler)
        submitButton = findViewById(R.id.button_finish)
        cancelButton = findViewById(R.id.button_cancel)

        previewName = findViewById(R.id.preview_name)
        previewColor = findViewById(R.id.preview_color)
        previewCategoryName = findViewById(R.id.preview_category)
        previewAmount = findViewById(R.id.preview_amount)
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
}