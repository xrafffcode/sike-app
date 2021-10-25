package com.sike.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button

class SignUpTre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_tre)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val btnsignuptre = findViewById<Button>(R.id.btnsignuptre)

        btnsignuptre.setOnClickListener {
            val intbtntre = Intent(this, SignUpFinish::class.java)
            startActivity(intbtntre)
        }
    }
}