package com.example.exchangeabletoken.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.exchangeabletoken.R
import com.example.exchangeabletoken.ui.market.MarketActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser

private lateinit var auth: FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val passwordLayout : TextInputLayout by lazy {
        findViewById(R.id.log_in_password_layout)
    }
    private val passwordET : TextInputEditText by lazy {
        findViewById(R.id.log_in_password)
    }
    private val emailLayout : TextInputLayout by lazy {
        findViewById(R.id.log_in_email_layout)
    }
    private val emailET : TextInputEditText by lazy {
        findViewById(R.id.log_in_email)
    }
    private lateinit var auth: FirebaseAuth

    // draw login activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        // initialize firebase auth
        auth = FirebaseAuth.getInstance()

        disableErrors()

        // add logic to login button
        val loginButton = findViewById<Button>(R.id.log_in_button)
        loginButton.setOnClickListener {
            if(emailValidation()){
                login()
            }

        }
    }

    private fun login() {
        // get email and password from text fields
        val email = emailET.text.toString()
        val password = passwordET.text.toString()


        // sign in with email and password
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                        try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthInvalidUserException) {
                            emailLayout.error = "Email does not exists"

                        } catch (e: FirebaseAuthException){

                            passwordLayout.error = "Wrong password"

                        }

                }
            }
    }

    private fun emailValidation() : Boolean{
        if(emailET.text.toString() != "" && !emailET.text.toString().contains(" ")){
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailET.text.toString()).matches())
            {
                emailLayout.error = "Correct form of email needed"
                return false
            }
            else{
                emailLayout.error = null
                return true

            }
        }
        else if(emailET.text.toString() == ""){
            emailLayout.error = "Please enter email"
            return false
        }
        else if(emailET.text.toString().contains(" ")){
            emailLayout.error = "Email mustn't contain spaces"
            return false
        }
        else{
            return false
        }
    }
    private fun disableErrors(){
        emailET.addTextChangedListener {
            emailLayout.error = null
        }
        passwordET.addTextChangedListener {
            passwordLayout.error = null
        }

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
