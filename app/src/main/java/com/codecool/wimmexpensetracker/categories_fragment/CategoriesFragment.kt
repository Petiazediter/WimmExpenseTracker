package com.codecool.wimmexpensetracker.categories_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.adapters.RecyclerAdapter
import com.codecool.wimmexpensetracker.adapters.RecyclerAdapterContractor
import com.codecool.wimmexpensetracker.data.CategoryColor
import com.codecool.wimmexpensetracker.mvvm.view_models.CategoriesViewModel
import com.codecool.wimmexpensetracker.new_category_activity.AddCategoryActivity
import com.codecool.wimmexpensetracker.product_activity.ActivityButtonListener
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor
import com.codecool.wimmexpensetracker.room_db.Category
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CategoriesFragment : Fragment(), ActivityButtonListener,RecyclerAdapterContractor {

    private var categoryRecyclerView: RecyclerView? = null
    private lateinit var recyclerAdapter : RecyclerAdapter
    private val viewModel : CategoriesViewModel by viewModel()

    override fun onButtonPressed() {
        val intent = Intent(context,AddCategoryActivity::class.java)
        startActivity(intent)
    }

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

        viewModel.getAllCategories(viewLifecycleOwner).observe(viewLifecycleOwner, {
            setRecyclerData(it)
        })
    }

    private fun bindViews(){
        categoryRecyclerView = view?.findViewById(R.id.recycler_view)
    }

    private fun setUpRecycler(){
        context?.let{
            recyclerAdapter = RecyclerAdapter(listOf(), layoutInflater, it,this, viewLifecycleOwner)
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

    override fun onItemDelteted(category: Category) {
        viewModel.deleteCategory(category)
    }

}