package com.example.exchangeabletoken.ui.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeabletoken.R

class SuccessfulSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successful_sign_up)

        // set onClickListener for the button
        findViewById<android.widget.Button>(R.id.go_to_log_in).setOnClickListener {
            val intent = android.content.Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
