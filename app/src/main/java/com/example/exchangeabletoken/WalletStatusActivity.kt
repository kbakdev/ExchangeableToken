package com.example.exchangeabletoken

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class WalletStatusActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_status)
        val user = FirebaseAuth.getInstance().currentUser

        // use realtime database to get user's balance
        val balance = findViewById<TextView>(R.id.balance)
        // get user's balance from Firebase realtime database
        val database = Firebase.database
        val myRef = database.getReference("users")
        myRef.child(user?.uid.toString()).child("balance").get()
            .addOnSuccessListener {
                balance.text = "Balance: ${it.value}"
            }
            .addOnFailureListener {
                balance.text = "Balance: 0"
            }
        val output = findViewById<TextView>(R.id.output)
        // get user's output from database
        FirebaseStorage.getInstance().reference.child("users/${user?.uid}/output").downloadUrl.addOnSuccessListener {
            output.text = "Output: $it"
        }
    }
}