package com.codecool.wimmexpensetracker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.data.CategoryColor
import com.codecool.wimmexpensetracker.room_db.Category
import com.codecool.wimmexpensetracker.room_db.Expense

class ExpenseAdapter(private val layoutInflater: LayoutInflater,
                     private val context : Context,
                     private var items : List<Expense>,
                     private var categories : List<Category> )
    : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(private val view : View) : RecyclerView.ViewHolder(view){

        private lateinit var expense : Expense
        private var category : Category? = null
        private lateinit var colorContainer : ImageView
        private lateinit var priceTextView : TextView
        private lateinit var titleTextView : TextView
        private lateinit var categoryNameTextView: TextView

        fun init(expense: Expense,category: Category?){
            this.expense = expense
            this.category = category
            bindViews()
            fillData()
        }

        private fun bindViews(){
            colorContainer = view.findViewById(R.id.color_container)
            priceTextView = view.findViewById(R.id.price)
            titleTextView = view.findViewById(R.id.title)
            categoryNameTextView = view.findViewById(R.id.category_name)
        }

        private fun fillData(){
            setCategoryColor()
            priceTextView.text = "$${expense.amount}"
            titleTextView.text = expense.title
            categoryNameTextView.text = category?.categoryName
        }

        private fun setCategoryColor() {
            category?.let { itemCategory ->
                colorContainer.setBackgroundColor(
                    when (itemCategory.colorId) {
                        CategoryColor.RED -> R.color.color_lightRed
                        CategoryColor.PINK -> R.color.color_lightPink
                        CategoryColor.GREEN -> R.color.color_lightGreen
                        CategoryColor.BLUE -> R.color.color_lightBlue
                        CategoryColor.YELLOW -> R.color.color_lightYellow
                    }
                )
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(layoutInflater.inflate(R.layout.expense_recycler_row, parent,false))
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = items[position]
        holder.init(expense,getCategoryById(expense.uid))
    }

    override fun getItemCount(): Int = items.size

    private fun getCategoryById(uuid: String ) : Category?{
        val filteredList = categories.filter{ category -> category.uId === uuid }
        return if ( filteredList.isNullOrEmpty())  null else filteredList[0]
    }
}