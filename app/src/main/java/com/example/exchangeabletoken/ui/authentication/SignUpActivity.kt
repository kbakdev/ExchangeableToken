package com.example.exchangeabletoken.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import java.util.*

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
            // validateInputs
            if (!validateInputs(email, password, confirmPassword, name, phone, address)) {
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
                            // set the user's role to user
                            myRef.child(name.text.toString().toLowerCase()).child("role").setValue("user")
                            // set the user's status to active
                            myRef.child(name.text.toString().toLowerCase()).child("status").setValue("active")
                            // set the user's displayName to his name
                            auth.currentUser?.updateProfile(
                                com.google.firebase.auth.UserProfileChangeRequest.Builder()
                                    .setDisplayName(name.text.toString().toLowerCase())
                                    .build()
                            )
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

    private fun validateInputs(
        email: EditText = findViewById(R.id.email),
        password: EditText = findViewById(R.id.password),
        confirmPassword: EditText = findViewById(R.id.confirmPassword),
        name: EditText = findViewById(R.id.name),
        phone: EditText = findViewById(R.id.phone),
        address: EditText = findViewById(R.id.postalAddress),
    ): Boolean {
        val inputs = arrayOf(email, password, confirmPassword, name, phone, address)
        val messages = arrayOf(
            "Please enter your email.",
            "Please enter your password.",
            "Please confirm your password.",
            "Please enter your name.",
            "Please enter your phone number.",
            "Please enter your postal address."
        )
        for ((index, input) in inputs.withIndex()) {
            if (input.text.toString().isEmpty()) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    messages[index],
                    Snackbar.LENGTH_SHORT
                ).show()
                // use Toast
                Toast.makeText(this, messages[index], Toast.LENGTH_SHORT).show()
                // use AlertDialog
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage(messages[index])
                builder.setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                builder.show()

                return false
            }
        }
        return true
    }
}
