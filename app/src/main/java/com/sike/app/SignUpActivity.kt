package com.sike.app

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity:AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        auth = FirebaseAuth.getInstance()

        val btnsignup = findViewById<Button>(R.id.btnsignup)
        val show = findViewById<TextView>(R.id.showHideBtn2)
        val signupemail = findViewById<EditText>(R.id.signupemail)
        val signuppw = findViewById<EditText>(R.id.signuppw)
        val have = findViewById<TextView>(R.id.haveSign)

        have.setOnClickListener {
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
        }

        btnsignup.setOnClickListener {
            val email = signupemail.text.toString().trim()
            val password = signuppw.text.toString().trim()

            if (email.isEmpty()){
                signupemail.error = "Email Harus Diisi"
                signupemail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                signupemail.error = "Email Tidak Valid"
                signupemail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                signuppw.error = "Password harus lebih dari 6 karakter"
                signuppw.requestFocus()
                return@setOnClickListener
            }

            register(email, password)
        }




        show.setOnClickListener{
            if (show.text.toString().equals("Show")){
                signuppw.transformationMethod = HideReturnsTransformationMethod.getInstance()
                show.text = "Hide"
            }
            else{
                signuppw.transformationMethod = PasswordTransformationMethod.getInstance()
                show.text = "Show"
            }
        }

    }

    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Intent(this, SignUpFinish::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this,it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }


}