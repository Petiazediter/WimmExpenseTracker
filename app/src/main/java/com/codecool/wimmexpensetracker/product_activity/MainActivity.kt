package com.codecool.wimmexpensetracker.product_activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.categories_fragment.CategoriesFragment
import com.codecool.wimmexpensetracker.data.LocalDatas
import com.codecool.wimmexpensetracker.expenses_fragment.ExpensesFragment
import com.codecool.wimmexpensetracker.home_fragment.HomeFragment
import com.codecool.wimmexpensetracker.settings_fragment.SettingsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), MainActivityContractor {

    companion object{
        lateinit var localDatas: LocalDatas
    }

    private val tabs = listOf(
        FragmentWrapper(HomeFragment(), R.string.home_fragment_title, R.drawable.ic_baseline_home_24, R.color.color_lightBlue),
        FragmentWrapper(ExpensesFragment(), R.string.expenses_fragment_title, R.drawable.ic_baseline_account_balance_wallet_24, R.color.color_lightGreen ),
        FragmentWrapper(CategoriesFragment(), R.string.categories, R.drawable.ic_baseline_category_24, R.color.color_lightRed),
        FragmentWrapper(SettingsFragment(), R.string.settings_fragment_title, R.drawable.ic_baseline_settings_24, R.color.color_lightYellow)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        localDatas = LocalDatas.getInstance(applicationContext,this@MainActivity)

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

        findViewById<TextView>(R.id.sub_menu).setOnClickListener {
            if ( tabs[viewPager2.currentItem].fragment is ActivityButtonListener){
                val listener =  tabs[viewPager2.currentItem].fragment as ActivityButtonListener
                listener.onButtonPressed()
            }
        }

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
