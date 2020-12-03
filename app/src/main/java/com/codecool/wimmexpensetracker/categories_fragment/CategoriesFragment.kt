package com.codecool.wimmexpensetracker.categories_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor

class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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