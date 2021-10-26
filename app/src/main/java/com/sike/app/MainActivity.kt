package com.sike.app

import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.sike.app.R

class MainActivity : AppCompatActivity() {

    private lateinit var midView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_animation)

        midView =findViewById(R.id.middletext)

        midView.startAnimation(fadeAnimation)

        val splashTo = 3000
        val homeIntent = Intent(this@MainActivity, OnboardOneActicity::class.java)

        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        }, splashTo.toLong())



    }


}