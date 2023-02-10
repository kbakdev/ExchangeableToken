package com.example.exchangeabletoken.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeabletoken.R
import com.example.exchangeabletoken.ui.market.MarketActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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
            // check if user is providing email
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

            // check if user with the same name doesn't already exist
            val database = Firebase.database
            val myRef = database.getReference("users")

            myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.hasChild(name.text.toString())) {
                        Snackbar.make(it, "User with the same name already exists", Snackbar.LENGTH_SHORT).show()
                        return
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException())
                }
            })

            if (password.text.toString() == confirmPassword.text.toString()) {
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // save new user's data to realtime database under their email
                            // save the data lowercase
                            myRef.child(name.text.toString().toLowerCase()).child("email").setValue(email.text.toString().toLowerCase())
                            myRef.child(name.text.toString().toLowerCase()).child("name").setValue(name.text.toString().toLowerCase())
                            myRef.child(name.text.toString().toLowerCase()).child("phone").setValue(phone.text.toString())
                            myRef.child(name.text.toString().toLowerCase()).child("address").setValue(address.text.toString().toLowerCase())
                            myRef.child(name.text.toString().toLowerCase()).child("uid").setValue(auth.currentUser?.uid.toString())
                            myRef.child(name.text.toString().toLowerCase()).child("balance").setValue(0)
                            // show that sign up is successful
                            Snackbar.make(it, "Sign up successful", Snackbar.LENGTH_SHORT).show()
                            // go to market activity
                            val intent = Intent(this, MarketActivity::class.java)
                            startActivity(intent)
                        } else {
                            Snackbar.make(it, "Sign up failed", Snackbar.LENGTH_SHORT).show()
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