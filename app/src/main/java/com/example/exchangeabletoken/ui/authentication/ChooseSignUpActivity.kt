package com.example.exchangeabletoken.ui.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeabletoken.R


class ChooseSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_sign_up)
        // set listener for login button
        findViewById<android.widget.Button>(R.id.log_in_button).setOnClickListener {
            // if user is already logged in, go to market activity
            if (com.example.exchangeabletoken.data.model.User.isLoggedIn()) {
                val intent = Intent(this, com.example.exchangeabletoken.ui.market.MarketActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        // set listener for sign up button
        findViewById<android.widget.Button>(R.id.sign_up_button).setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}