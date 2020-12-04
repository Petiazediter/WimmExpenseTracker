package com.codecool.wimmexpensetracker.adapters

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.data.CategoryColor
import com.codecool.wimmexpensetracker.room_db.Category

class NewExpenseRecyclerAdapter(
    private val context: Context,
    private val layoutInflater: LayoutInflater,
    var list: List<Category>,
    private val view : NewExpensesRecyclerContractor
) : RecyclerView.Adapter<NewExpenseRecyclerAdapter.NewExpenseViewHolder>() {

    class NewExpenseViewHolder(private val itemView: View,
                               private val context: Context,
                               private val parentView : NewExpensesRecyclerContractor)
        : RecyclerView.ViewHolder(itemView) {

        lateinit var viewContainer : ConstraintLayout
        lateinit var colorContainer : ImageView
        lateinit var deleteButton: ImageView
        lateinit var nameTextView: TextView
        lateinit var totalExpense: TextView
        lateinit var monthExpense: TextView

        fun init(category: Category) {
            bindViews()
            setColor(category.colorId)
            hideDelButton()
            setTitle(category.categoryName)
            setDescriptions()
            addClickListener(category)
        }

        private fun addClickListener(category: Category){
            viewContainer.setOnClickListener {
                parentView.onCategoryClicked(category = category)
            }
        }

        private fun hideDelButton(){deleteButton.visibility = View.GONE}

        private fun setColor(categoryColor: CategoryColor){
            when ( categoryColor){
                (CategoryColor.BLUE) ->
                    colorContainer.setBackgroundColor(
                        context.resources.getColor(
                            R.color.color_lightBlue,context.theme))

                (CategoryColor.RED) ->
                    colorContainer.setBackgroundColor(
                        context.resources.getColor(
                            R.color.color_lightRed,context.theme))

                (CategoryColor.GREEN) ->
                    colorContainer.setBackgroundColor(
                        context.resources.getColor(
                            R.color.color_lightGreen,context.theme))

                (CategoryColor.YELLOW) ->
                    colorContainer.setBackgroundColor(
                        context.resources.getColor(
                            R.color.color_lightYellow,context.theme))

                (CategoryColor.PINK) ->
                    colorContainer.setBackgroundColor(
                        context.resources.getColor(
                            R.color.color_lightPink,context.theme))
                else ->
                    colorContainer.setBackgroundColor(
                        context.resources.getColor(
                            R.color.color_lightBlue,context.theme))
            }
        }

        private fun setTitle(name : String) { nameTextView.text = name }

        private fun setDescriptions(){
            totalExpense.text = context.resources.getString(R.string.preview_text_2)
            monthExpense.text = context.resources.getString(R.string.preview_text_3)
        }

        private fun bindViews() {
            deleteButton = itemView.findViewById(R.id.delete_button)
            nameTextView = itemView.findViewById(R.id.category_name)
            totalExpense = itemView.findViewById(R.id.total_expense)
            monthExpense = itemView.findViewById(R.id.current_month_expense)
            colorContainer = itemView.findViewById(R.id.color_container)
            viewContainer = itemView.findViewById(R.id.view_container)
        }

    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : NewExpenseViewHolder =
        NewExpenseViewHolder(
            layoutInflater.inflate(
                R.layout.category_recycler_row,
                parent, false
            ),
            context,
            view
        )

    override fun onBindViewHolder(holder: NewExpenseViewHolder, position: Int) {
        val category = list[position]
        holder.init(category)
    }
}