package com.codecool.wimmexpensetracker.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.data.CategoryColor
import com.codecool.wimmexpensetracker.room_db.Category
import com.codecool.wimmexpensetracker.room_db.Expense

class ExpenseAdapter(private val layoutInflater: LayoutInflater,
                     val context: Context,
                     var items : List<Expense>,
                     var categories : List<Category>
)
    : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(private val view : View, private val context: Context)
        : RecyclerView.ViewHolder(view){

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

        }

        private fun setCategoryColor() {
            category?.let{
                    colorContainer.setBackgroundColor(context.resources.getColor(
                        when (it.colorId) {
                            CategoryColor.RED -> R.color.color_lightRed
                            CategoryColor.PINK -> R.color.color_lightPink
                            CategoryColor.GREEN -> R.color.color_lightGreen
                            CategoryColor.BLUE -> R.color.color_lightBlue
                            CategoryColor.YELLOW -> R.color.color_lightYellow
                        }
                    ))
                categoryNameTextView.text = category!!.categoryName
            } ?: run{
                colorContainer.setBackgroundColor(context.resources.getColor( R.color.color_lightRed,context.theme))
                categoryNameTextView.text = context.getString(R.string.category_deleted)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(layoutInflater.inflate(R.layout.expense_recycler_row, parent,false),context)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = items[position]
        holder.init(expense,getCategoryById(expense.expenseCategory))
    }

    override fun getItemCount(): Int = items.size

    private fun getCategoryById(uuid: String ) : Category?{
        val filteredList = categories.filter{ category -> category.uId == uuid }
        return if ( filteredList.isNullOrEmpty()) null else filteredList[0]
    }
}