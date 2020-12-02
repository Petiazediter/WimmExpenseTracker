package com.codecool.wimmexpensetracker.product_activity

import androidx.fragment.app.Fragment

data class FragmentWrapper(
        val fragment : Fragment,
        val title : Int,
        val drawableId : Int,
        val color : Int
)