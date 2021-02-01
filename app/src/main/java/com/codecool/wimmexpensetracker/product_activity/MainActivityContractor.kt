package com.codecool.wimmexpensetracker.product_activity

interface MainActivityContractor {
    fun setMenuTitle( pageTitle : String)
    fun setSubMenuTitle ( menuTitle : String)
    fun changeToFragment ( page : Int)
}