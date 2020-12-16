package com.codecool.wimmexpensetracker.new_expense_activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
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
import com.codecool.wimmexpensetracker.mvvm.view_models.NewExpenseViewModel
import com.codecool.wimmexpensetracker.room_db.Category
import com.codecool.wimmexpensetracker.room_db.Expense
import org.koin.android.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.util.*

class NewExpenseActivity : AppCompatActivity(), NewExpensesRecyclerContractor {

    private var nameEditText: EditText? = null
    private var amountEditText: EditText? = null

    private var categoryRecyclerView: RecyclerView? = null
    private var submitButton: Button? = null
    private var cancelButton: Button? = null

    private val categoriesViewModel: CategoriesViewModel by viewModel()

    private lateinit var newExpenseViewModel: NewExpenseViewModel
    private lateinit var recyclerAdapter: NewExpenseRecyclerAdapter

    private var previewName: TextView? = null
    private var previewCategoryName: TextView? = null
    private var previewColor: ImageView? = null
    private var previewAmount: TextView? = null
    private var previewDate: TextView? = null

    private var categoryChoosed: Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_expense)

        //categoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        newExpenseViewModel = ViewModelProvider(this)[NewExpenseViewModel::class.java]

        newExpenseViewModel.init()
        //categoriesViewModel.init(this)

        categoryChoosed = null

        bindViews()
        setUpRecycler()
        setRecyclerData()

        addEditTextFunctions()

        cancelButton?.setOnClickListener { finish() }
        submitButton?.setOnClickListener{
            onFinishButtonClick()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addEditTextFunctions() {
        nameEditText?.addTextChangedListener {
            previewName?.text = it.toString()
        }

        amountEditText?.addTextChangedListener {
            previewAmount?.text = "$${it.toString()}"
        }
    }


    override fun onCategoryClicked(category: Category) {
        val color = when (category.colorId) {
            CategoryColor.PINK -> R.color.color_lightPink
            CategoryColor.YELLOW -> R.color.color_lightYellow
            CategoryColor.GREEN -> R.color.color_lightGreen
            CategoryColor.RED -> R.color.color_lightRed
            CategoryColor.BLUE -> R.color.color_lightBlue
        }
        previewColor?.setBackgroundColor(applicationContext.getColor(color))
        previewCategoryName?.text = category.categoryName
        categoryChoosed = category
    }

    @SuppressLint("SetTextI18n")
    private fun bindViews() {
        nameEditText = findViewById(R.id.title_et)
        amountEditText = findViewById(R.id.amount_et)

        categoryRecyclerView = findViewById(R.id.category_recycler)
        submitButton = findViewById(R.id.button_finish)
        cancelButton = findViewById(R.id.button_cancel)

        previewName = findViewById(R.id.preview_name)
        previewColor = findViewById(R.id.preview_color)
        previewCategoryName = findViewById(R.id.preview_category)
        previewAmount = findViewById(R.id.preview_amount)
        previewDate = findViewById(R.id.preview_date)

        previewDate?.text =
            "${LocalDate.now().dayOfMonth},${LocalDate.now().month.name},${LocalDate.now().year}"
    }


    private fun setRecyclerData() {
        categoriesViewModel.getAllCategories(this).observe(this, {
            recyclerAdapter.list = it
            recyclerAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpRecycler() {
        recyclerAdapter = NewExpenseRecyclerAdapter(
            applicationContext,
            layoutInflater, listOf(), this
        )

        categoryRecyclerView?.adapter = recyclerAdapter
        categoryRecyclerView?.layoutManager = LinearLayoutManager(
            applicationContext, LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun onFinishButtonClick() {
        categoryChoosed?.let { mCategory ->
            previewName?.let { mPreviewName ->
                previewCategoryName?.let { mPreviewCategoryName ->
                    previewAmount?.let { mPreviewAmount ->
                        if (mPreviewName.text.isNotBlank() && mPreviewName.text.length >= 4) {

                            // Delete the $ from the start
                            val string = mPreviewAmount.text.toString().substring(1)

                            var amount : Float? = string.toFloatOrNull()

                            amount?.let {theAmount ->
                                if (theAmount > 0) {
                                    val expense = Expense(
                                        uid = UUID.randomUUID().toString(),
                                        title = mPreviewName.text.toString(),
                                        year = LocalDate.now().year,
                                        month = LocalDate.now().monthValue,
                                        day = LocalDate.now().dayOfMonth,
                                        amount = theAmount,
                                        expenseCategory = mCategory.uId
                                    )
                                    newExpenseViewModel.addExpense(expense)
                                    finish()
                                } else {
                                    Toast.makeText(applicationContext,
                                    resources.getString(R.string.price),Toast.LENGTH_SHORT).show()
                                }
                            } ?: run{
                                Toast.makeText(applicationContext, resources.getString(
                                    R.string.make_sure_amount),
                                    Toast.LENGTH_SHORT
                                ).show() }
                        } else {
                            Toast.makeText(applicationContext, resources.getString(
                                R.string.too_short_name),
                                Toast.LENGTH_LONG)
                                .show() }
                    }
                }
            }
        }
    }
}