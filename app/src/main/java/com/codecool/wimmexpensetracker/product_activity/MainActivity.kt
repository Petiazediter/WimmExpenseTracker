package com.codecool.wimmexpensetracker.product_activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.home_fragment.HomeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), MainActivityContractor {

    val tabs = listOf(
            FragmentWrapper(HomeFragment(), R.string.home_fragment_title,R.drawable.ic_baseline_home_24)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager_2)

        viewPager2.adapter = ViewPagerAdapter(tabs,supportFragmentManager,lifecycle)

        TabLayoutMediator(tabLayout,viewPager2){tab,position ->
            tab.text = resources.getString(tabs[position].title)
            tab.icon = ContextCompat.getDrawable(applicationContext, tabs[position].drawableId)
        }.attach()
    }

    override fun setMenuTitle(menuTitle: String) {
        findViewById<TextView>(R.id.menu_title)
                .text = menuTitle
    }

    override fun setPageTitle(pageTitle: String) {
        findViewById<TextView>(R.id.sub_menu)
                .text = pageTitle
    }
}
