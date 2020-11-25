package com.codecool.wimmexpensetracker.product_activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.codecool.wimmexpensetracker.R

class MainActivity : AppCompatActivity(), MainActivityContractor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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