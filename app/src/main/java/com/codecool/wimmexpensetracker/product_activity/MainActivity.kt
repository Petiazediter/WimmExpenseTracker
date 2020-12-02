package com.codecool.wimmexpensetracker.product_activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.categories_fragment.CategoriesFragment
import com.codecool.wimmexpensetracker.home_fragment.HomeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), MainActivityContractor {

    private val tabs = listOf(
            FragmentWrapper(HomeFragment(), R.string.home_fragment_title, R.drawable.ic_baseline_home_24, R.color.color_lightBlue),
            FragmentWrapper(CategoriesFragment(), R.string.categories, R.drawable.ic_baseline_category_24, R.color.color_lightRed)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager_2)

        viewPager2.adapter = ViewPagerAdapter(tabs, supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = resources.getString(tabs[position].title)
            tab.icon = ContextCompat.getDrawable(applicationContext, tabs[position].drawableId)
            tab.icon?.setTint( if (position == 0) resources.getColor(tabs[position].color,theme) else resources.getColor(R.color.med_gray,theme))
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(resources.getColor(tabs[tab.position].color,theme))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(resources.getColor(R.color.med_gray,theme))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun setSubMenuTitle(menuTitle: String) {
        findViewById<TextView>(R.id.sub_menu)
                .text = menuTitle
    }

    override fun setMenuTitle(pageTitle: String) {
        findViewById<TextView>(R.id.menu_title)
                .text = pageTitle
    }
}
