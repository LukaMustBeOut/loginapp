package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChangeData : AppCompatActivity() {

    private lateinit var usernameEditText: EditText

    private lateinit var saveButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_data)

        init()
    }

    private fun init(){
        usernameEditText = findViewById(R.id.usernameEditText)

        saveButton = findViewById(R.id.saveButton)
    }

    private fun registeredListeners() {
        saveButton.setOnClickListener {
            val username = usernameEditText.text.toString()

            if (username.isEmpty()) {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(username)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "check email!", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
                    }
        }


    }
}