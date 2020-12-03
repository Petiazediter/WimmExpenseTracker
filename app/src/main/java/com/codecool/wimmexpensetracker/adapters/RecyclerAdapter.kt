package com.codecool.wimmexpensetracker.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.data.CategoryColor
import com.codecool.wimmexpensetracker.home_fragment.HomeFragment.Companion.formatTo2Decimals
import com.codecool.wimmexpensetracker.mvvm.view_models.HomeFragmentViewModel
import com.codecool.wimmexpensetracker.mvvm.view_models.RecyclerAdapterViewModel
import com.codecool.wimmexpensetracker.room_db.Category
import java.time.LocalDateTime

class RecyclerAdapter (var list : List<Category>, private val layoutInflater: LayoutInflater, private val context : Context, private val view : RecyclerAdapterContractor,private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>() {

    class ViewHolderClass(itemView : View, val context: Context,val lifecycleOwner: LifecycleOwner) : RecyclerView.ViewHolder(itemView) {

        private lateinit var colorContainer : ImageView
        private lateinit var categoryName : TextView
        private lateinit var totalExpense : TextView
        private lateinit var currentMonthExpense : TextView
        private lateinit var deleteButton : ImageView

        private lateinit var parentView : RecyclerAdapterContractor

        private lateinit var viewModel : RecyclerAdapterViewModel

        @SuppressLint("SetTextI18n")
        fun init(category: Category, view : RecyclerAdapterContractor){
            parentView = view
            bindViews()
            setSideColor(category.colorId)
            categoryName.text = category.categoryName

            deleteButton.setOnClickListener {
                parentView.onItemDelteted(category)
            }

            viewModel = ViewModelProvider(context as ViewModelStoreOwner).get(RecyclerAdapterViewModel::class.java)
            viewModel.init(lifecycleOwner)
            viewModel.getExpensesByCategory(category)?.observe(lifecycleOwner,{
                totalExpense.text = context.resources.getString(R.string.total_expenses) + it.map{it.amount}.sum().formatTo2Decimals()
                currentMonthExpense.text = context.resources.getString(R.string.current_month_expenses) + (it.filter{ it.year == LocalDateTime.now().year && it.month == LocalDateTime.now().monthValue }.map{it.amount}.sum()).formatTo2Decimals()
            })
        }

        private fun bindViews(){
            colorContainer = itemView.findViewById(R.id.color_container)
            categoryName = itemView.findViewById(R.id.category_name)
            totalExpense = itemView.findViewById(R.id.total_expense)
            deleteButton = itemView.findViewById(R.id.delete_button)
            currentMonthExpense = itemView.findViewById(R.id.current_month_expense)
        }

        private fun setSideColor(color : CategoryColor){
            when ( color ) {
                CategoryColor.BLUE -> colorContainer.setBackgroundColor(context.resources.getColor(R.color.color_lightBlue, context.theme))
                CategoryColor.GREEN -> colorContainer.setBackgroundColor(context.resources.getColor(R.color.color_lightGreen, context.theme))
                CategoryColor.RED -> colorContainer.setBackgroundColor(context.resources.getColor(R.color.color_lightRed, context.theme))
                CategoryColor.PINK -> colorContainer.setBackgroundColor(context.resources.getColor(R.color.color_lightPink, context.theme))
                CategoryColor.YELLOW -> colorContainer.setBackgroundColor(context.resources.getColor(R.color.color_lightYellow, context.theme))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass = ViewHolderClass( layoutInflater.inflate(R.layout.category_recycler_row,parent,false), context,lifecycleOwner)

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val category : Category = list[position]
        holder.init(category,view)
    }

    override fun getItemCount(): Int = list.size
}