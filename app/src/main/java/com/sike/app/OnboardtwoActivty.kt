package com.sike.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class OnboardtwoActivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboard2_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val  btnto3 = findViewById<Button>(R.id.btn_to3)
        btnto3.setOnClickListener {
            val intto3 = Intent(this, OnboardTreActivity::class.java)
            startActivity(intto3)
        }

        val ilus2 = findViewById<ImageView>(R.id.ilus2)
        val buttom = findViewById<View>(R.id.rectangle_4)
        val tv1 = findViewById<TextView>(R.id.textView)
        val tv2 = findViewById<TextView>(R.id.textView2)
        val btn = findViewById<Button>(R.id.btn_to3)
        val sv1 = findViewById<ImageView>(R.id.slideview1)

        val stb = AnimationUtils.loadAnimation(this,R.anim.stb)
        val btt = AnimationUtils.loadAnimation(this,R.anim.btt)
        val fade = AnimationUtils.loadAnimation(this,R.anim.fade_animation)

        ilus2.startAnimation(stb)
        buttom.startAnimation(btt)
        tv1.startAnimation(btt)
        tv2.startAnimation(btt)
        btn.startAnimation(btt)
        sv1.startAnimation(fade)


    }

}