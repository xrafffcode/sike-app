package com.sike.app

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.WindowManager
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val show = findViewById<TextView>(R.id.showHideBtn)
        val signpw = findViewById<EditText>(R.id.signpw)
        val signemail = findViewById<EditText>(R.id.signemail)
        val btnsign = findViewById<Button>(R.id.signin)
        val nothave = findViewById<TextView>(R.id.nothaveSignUp)
        val forgot = findViewById<TextView>(R.id.forgotpw)

        forgot.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        nothave.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }


        btnsign.setOnClickListener{
            val email = signemail.text.toString().trim()
            val password = signpw.text.toString().trim()

            if (email.isEmpty()){
                signemail.error = "Email Harus Diisi"
                signemail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                signemail.error = "Email Tidak Valid"
                signemail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                signpw.error = "Password harus lebih dari 6 karakter"
                signpw.requestFocus()
                return@setOnClickListener
            }

            loginUser(email, password)

        }


        show.setOnClickListener{
            if (show.text.toString().equals("Show")){
                signpw.transformationMethod = HideReturnsTransformationMethod.getInstance()
                show.text = "Hide"
            }
            else{
                signpw.transformationMethod = PasswordTransformationMethod.getInstance()
                show.text = "Show"
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Intent(this@SignIn, HomeActivity::class.java).also {intent ->
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }else{
                    Toast.makeText(this,"${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }


}