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
import com.google.firebase.auth.FirebaseAuth


class OnboardOneActicity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("CutPasteId")
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboard1_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val btndtl: Button = findViewById(R.id.btn_to2)
        btndtl.setOnClickListener(this)

        val ttb = AnimationUtils.loadAnimation(this,R.anim.ttb)
        val stb = AnimationUtils.loadAnimation(this,R.anim.stb)
        val btt = AnimationUtils.loadAnimation(this,R.anim.btt)
        val btn = findViewById<Button>(R.id.btn_to2)

        val welcome = findViewById<TextView>(R.id.tv_welcome)
        val subs = findViewById<TextView>(R.id.tv_desc)
        val ilus1 = findViewById<ImageView>(R.id.iv_ilus1)

        auth = FirebaseAuth.getInstance()

        welcome.startAnimation(ttb)
        subs.startAnimation(ttb)
        ilus1.startAnimation(stb)
        btn.startAnimation(btt)
    }



    override fun onClick(v: View?) {
        if (v != null)
            when(v.id){
                R.id.btn_to2 -> kotlin.run {
                    val intentbiasa = Intent(this, OnboardtwoActivty::class.java)
                    startActivity(intentbiasa)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this, HomeActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}