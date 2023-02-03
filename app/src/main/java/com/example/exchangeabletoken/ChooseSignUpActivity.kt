package com.example.exchangeabletoken

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class ChooseSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_sign_up)
        // set listener for login button
        findViewById<android.widget.Button>(R.id.log_in_button).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // set listener for sign up button
        findViewById<android.widget.Button>(R.id.sign_up_button).setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}