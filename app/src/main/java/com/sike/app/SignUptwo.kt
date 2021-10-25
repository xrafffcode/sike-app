package com.sike.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button

class SignUptwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_uptwo)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val btnmale = findViewById<Button>(R.id.btnmale)
        val btnfemale = findViewById<Button>(R.id.btnfemale)

        btnmale.setOnClickListener{
            val intmale = Intent(this, SignUpTre::class.java)
            startActivity(intmale)
        }

        btnfemale.setOnClickListener {
            val intfemale = Intent(this, SignUpTre::class.java)
            startActivity(intfemale)
        }
    }
}