package com.codecool.wimmexpensetracker.splash_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.product_activity.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val companyTV = findViewById<TextView>(R.id.company_name)
        val productTV = findViewById<TextView>(R.id.product_name)
        val logoImageView = findViewById<ImageView>(R.id.logo)

        val animation = AnimationUtils.loadAnimation(applicationContext,R.anim.logo_animation)
        logoImageView.startAnimation(animation)

        val textAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.text_animations)
        companyTV.startAnimation(textAnimation)
        productTV.startAnimation(textAnimation)

        productTV.animation.setAnimationListener( object : Animation.AnimationListener{
            override fun onAnimationEnd(p0: Animation?) {
                Handler(Looper.getMainLooper()).postDelayed({
                    val activity = Intent(applicationContext,MainActivity::class.java)
                    startActivity(activity)
                    finish()
                },2000)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationStart(p0: Animation?) {

            }
        })
    }
}