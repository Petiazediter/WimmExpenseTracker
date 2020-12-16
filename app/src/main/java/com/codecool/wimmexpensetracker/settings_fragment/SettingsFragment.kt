package com.codecool.wimmexpensetracker.settings_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.product_activity.ActivityButtonListener
import com.codecool.wimmexpensetracker.product_activity.MainActivityContractor


class SettingsFragment : Fragment(),ActivityButtonListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onResume() {
        super.onResume()
        context?.let{ context ->
            (activity as MainActivityContractor).apply {
                setMenuTitle( context.getString(R.string.settings_fragment_title))
                setSubMenuTitle(context.getString(R.string.home_fragment_title))
            }
        }
    }

    override fun onButtonPressed() {
        // go to home fragment
    }


}