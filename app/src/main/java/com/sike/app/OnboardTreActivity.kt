package com.sike.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sike.app.R

class OnboardTreActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboard3_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val vbtm = findViewById<View>(R.id.rectangle_4)
        val tv3 = findViewById<TextView>(R.id.textView3)
        val tv6 = findViewById<TextView>(R.id.textView6)
        val signup = findViewById<Button>(R.id.button)
        val signin = findViewById<Button>(R.id.btn_sign)
        val ilus = findViewById<ImageView>(R.id.imageView)




        val stb = AnimationUtils.loadAnimation(this,R.anim.stb)
        val btt = AnimationUtils.loadAnimation(this,R.anim.btt)
        val fade = AnimationUtils.loadAnimation(this,R.anim.fade_animation)

        vbtm.startAnimation(btt)
        tv3.startAnimation(btt)
        tv6.startAnimation(btt)
        signup.startAnimation(btt)
        signin.startAnimation(btt)

        signup.setOnClickListener{
            val intsignup = Intent(this, SignUpActivity::class.java)
            startActivity(intsignup)
        }

        signin.setOnClickListener {
            val intsign = Intent(this, SignIn::class.java)
            startActivity(intsign)
        }


    }


}