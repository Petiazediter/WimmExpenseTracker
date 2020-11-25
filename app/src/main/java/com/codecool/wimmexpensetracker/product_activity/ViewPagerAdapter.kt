package com.codecool.wimmexpensetracker.product_activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter( val listOfFragments : List<FragmentWrapper>,fragmentManager: FragmentManager,lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int = listOfFragments.size
    override fun createFragment(position: Int): Fragment = listOfFragments[position].fragment
}