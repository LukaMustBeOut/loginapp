package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var emailEditText : TextView
    private lateinit var passwordEditText: TextView
    private lateinit var logInButton: Button
    private lateinit var registerButton: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        registerListeners()
    }

    private fun init(){

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        logInButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

    }

    private fun registerListeners(){

        registerButton.setOnClickListener {
            startActivity(Intent(this,Registration::class.java))
        }

        logInButton.setOnClickListener {

            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        goToProfile()
                    }else{
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }



        }
    }
}

    private fun goToProfile() {
        startActivity(Intent(this, Profile::class.java))
        finish()
    }
}
