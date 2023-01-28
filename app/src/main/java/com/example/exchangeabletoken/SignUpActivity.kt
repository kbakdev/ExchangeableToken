package com.example.exchangeabletoken

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    // declare firebase
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        // create an instance of firebase
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirmPassword)
        val name = findViewById<EditText>(R.id.name)
        val phone = findViewById<EditText>(R.id.phone)
        val address = findViewById<EditText>(R.id.postalAddress)
        val signUp = findViewById<Button>(R.id.signUpButton)
        signUp.setOnClickListener {
            // check if user is providig email
            if (email.text.toString().isEmpty()) {
                Snackbar.make(it, "Please enter email", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // check if user is providing password
            if (password.text.toString().isEmpty()) {
                Snackbar.make(it, "Please enter password", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // check if user is providing confirm password
            if (confirmPassword.text.toString().isEmpty()) {
                Snackbar.make(it, "Please enter confirm password", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // check if user is providing name
            if (name.text.toString().isEmpty()) {
                Snackbar.make(it, "Please enter name", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.text.toString() == confirmPassword.text.toString()) {
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // save data to realtime database
                            val database = Firebase.database
                            val myRef = database.getReference("users")
                            myRef.child(auth.currentUser?.uid.toString()).child("name").setValue(name.text.toString())
                            myRef.child(auth.currentUser?.uid.toString()).child("phone").setValue(phone.text.toString())
                            myRef.child(auth.currentUser?.uid.toString()).child("address").setValue(address.text.toString())
                            myRef.child(auth.currentUser?.uid.toString()).child("balance").setValue(0)

                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            val db = DataBaseService()
                            db.addUser(user!!.uid, name.text.toString(), email.text.toString(), phone.text.toString(), address.text.toString())
                            val intent = Intent(this, MarketActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Snackbar.make(
                                findViewById(android.R.id.content),
                                "Authentication Failed.",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Passwords do not match.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}