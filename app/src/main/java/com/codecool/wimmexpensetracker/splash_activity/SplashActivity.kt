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
import com.codecool.wimmexpensetracker.data.SharedPreferenceController
import com.codecool.wimmexpensetracker.product_activity.MainActivity
import com.codecool.wimmexpensetracker.room_db.AppDatabase

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

                // Create the database. It'll store it to the AppDatabase database variable
                // so don't need to get the database with context anymore.
                AppDatabase.getDatabase(applicationContext)
                SharedPreferenceController.setUpSharedPreferences(applicationContext)

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