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

class RecyclerAdapter (var list : List<Category>, private val layoutInflater: LayoutInflater, private val context : Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>() {

    class ViewHolderClass(itemView : View, val context: Context) : RecyclerView.ViewHolder(itemView) {

        private lateinit var colorContainer : ImageView
        private lateinit var categoryName : TextView
        private lateinit var totalExpense : TextView
        private lateinit var currentMonthExpense : TextView

        fun init(category: Category){
            bindViews()
            setSideColor(category.colorId)
            categoryName.text = category.categoryName
        }

        private fun bindViews(){
            colorContainer = itemView.findViewById(R.id.color_container)
            categoryName = itemView.findViewById(R.id.category_name)
            totalExpense = itemView.findViewById(R.id.total_expense)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass = ViewHolderClass( layoutInflater.inflate(R.layout.category_recycler_row,parent,false), context)

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val category : Category = list[position]
        holder.init(category)
    }

    override fun getItemCount(): Int = list.size
}