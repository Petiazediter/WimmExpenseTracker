package com.codecool.wimmexpensetracker.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor

class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivityContractor).let{
            it.setMenuTitle(resources.getString(R.string.home_fragment_title))
            it.setSubMenuTitle(resources.getString(R.string.settings_fragment_title))
        }
    }

}