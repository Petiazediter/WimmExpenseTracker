package com.codecool.wimmexpensetracker.categories_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.adapters.RecyclerAdapter
import com.codecool.wimmexpensetracker.data.CategoryColor
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor
import com.codecool.wimmexpensetracker.room_db.Category

class CategoriesFragment : Fragment() {

    private var categoryRecyclerView: RecyclerView? = null
    private lateinit var recyclerAdapter : RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        setUpRecycler()
        setRecyclerData( listOf(
                Category("ASD", "Name1", CategoryColor.YELLOW),
                Category( "ASDD", "Name2", CategoryColor.PINK),
                Category( "ASDD", "Name3", CategoryColor.GREEN),
                Category( "ASDD", "Name4", CategoryColor.RED),
                Category( "ASDD", "Name5", CategoryColor.BLUE),
                Category( "ASDD", "Name6", CategoryColor.RED),
        ))
    }

    private fun bindViews(){
        categoryRecyclerView = view?.findViewById(R.id.recycler_view)
    }

    private fun setUpRecycler(){
        context?.let{
            recyclerAdapter = RecyclerAdapter(listOf(), layoutInflater, it)
            categoryRecyclerView?.adapter = recyclerAdapter
            categoryRecyclerView?.layoutManager = LinearLayoutManager(it,LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setRecyclerData ( list : List<Category>){
        recyclerAdapter.list = list
        recyclerAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        if ( activity is MainActivityContractor){
            (activity as MainActivityContractor).let{
                it.setMenuTitle( resources.getString(R.string.categories) )
                it.setSubMenuTitle( resources.getString(R.string.add_categories))
            }
        }
    }

}