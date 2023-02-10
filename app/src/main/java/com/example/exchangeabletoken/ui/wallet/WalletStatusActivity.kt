package com.example.exchangeabletoken.ui.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.exchangeabletoken.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class WalletStatusActivity : AppCompatActivity() {
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
            .addOnSuccessListener { it ->
                "Balance: ${it.value}".also { balance.text = it }
            }
            .addOnFailureListener {
                "Balance: 0".also { balance.text = it }
            }
        val output = findViewById<TextView>(R.id.output)
        // get user's output from database
        FirebaseStorage.getInstance().reference.child("users/${user?.uid}/output").downloadUrl.addOnSuccessListener { it ->
            "Output: $it".also { output.text = it }
        }
    }
}