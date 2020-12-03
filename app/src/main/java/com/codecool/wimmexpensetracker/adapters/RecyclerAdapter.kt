package com.codecool.wimmexpensetracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.data.CategoryColor
import com.codecool.wimmexpensetracker.room_db.Category

class RecyclerAdapter (private val list : List<Category>, private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>() {

    class ViewHolderClass(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun init(category: Category){
            setSideColor(category.colorId)
        }

        private fun setSideColor(color : CategoryColor){
            //when ( color ){
               // CategoryColor.BLUE -> itemView.
            //}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass = ViewHolderClass( layoutInflater.inflate(R.layout.category_recycler_row,parent,false))

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val category : Category = list[position]
        holder.init(category)
    }

    override fun getItemCount(): Int = list.size
}