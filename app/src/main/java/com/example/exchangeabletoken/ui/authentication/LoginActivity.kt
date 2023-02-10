package com.example.exchangeabletoken.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeabletoken.R
import com.example.exchangeabletoken.ui.market.MarketActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private lateinit var auth: FirebaseAuth

class LoginActivity : AppCompatActivity() {

    // draw login activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        // initialize firebase auth
        auth = FirebaseAuth.getInstance()

        // add logic to login button
        val loginButton = findViewById<Button>(R.id.log_in_button)
        loginButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        // get email and password from text fields
        val email = findViewById<EditText>(R.id.log_in_email).text.toString()
        val password = findViewById<EditText>(R.id.log_in_password).text.toString()

        // check if email and password are empty
        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(findViewById(R.id.log_in_layout), "Please fill in all fields", Snackbar.LENGTH_LONG).show()
            return
        }

        // sign in with email and password
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // if sign in fails, display a message to the user.
                    Snackbar.make(findViewById(R.id.log_in_layout), "Authentication failed.", Snackbar.LENGTH_LONG).show()
                    updateUI(null)
                }
            }
        return
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            val intent = Intent(this, MarketActivity::class.java)
            startActivity(intent)
        }
    }
}
