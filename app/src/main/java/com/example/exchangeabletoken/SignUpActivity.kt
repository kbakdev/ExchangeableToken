package com.example.exchangeabletoken

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // email validation
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextTextPassword)

        // set onClickListener, on click button register with credentials
        // and go to SuccessfulSignUpActivity
        findViewById<Button>(R.id.register_with_credentials).setOnClickListener {

            // check if email is valid
            if (Validation.validateEmail(email.text.toString())) {
                // check if password is valid
                if (Validation.validatePassword(password.text.toString())) {
                    // go to SuccessfulSignUpActivity
                    val intent = Intent(this, SuccessfulSignUpActivity::class.java)
                    startActivity(intent)
                } else {
                    // show error message
                    password.error = "Password must be at least 6 characters"
                }
            } else {
                // show error message
                email.error = "Email is not valid"
            }
        }
    }
}