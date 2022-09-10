package com.example.exchangeabletoken

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.NestedScrollView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity() {
//    // Access to onscreen controls
//    private lateinit var binding: ActivityLoginBinding
//
//    // App and user Status
//    private lateinit var account: Account
//    private var isLoggedIn = false
//    private var appJustLaunched = true
//    private var userIsAuthenticated = false
//
//    // Account data
//    private var user = User()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val email = findViewById<EditText>(R.id.log_in_email_input)
        val password = findViewById<EditText>(R.id.log_in_password_input)

        findViewById<Button>(R.id.log_in_button).setOnClickListener {

            // check if email is valid
            if (email.text.toString().isNotEmpty()) {

                // check if password is valid
                if (password.text.toString().isNotEmpty()) {
                    // go to SuccessfulSignUpActivity
                    val intent = Intent(this, MarketActivity::class.java)
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
        // TODO: implement this
//        binding = ActivityLoginBinding.inflate(layoutInflater)
        // TODO: implement this
        // setContentView(binding.root)

//        account = Account("exchangeabletoken", "com.example.exchangeabletoken")
        // TODO: implement this
        //             getString(R.string.com_auth0_client_id),
        //            getString(R.string.com_auth0_domain)

        // TODO: implement this
        //        binding.loginButton.setOnClickListener { login() }
        //        binding.logoutButton.setOnClickListener { logout() }

    }
}
