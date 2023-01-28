package com.example.exchangeabletoken

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage

class WalletStatusActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_status)
        val user = FirebaseAuth.getInstance().currentUser
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        var walletName: String = ""
        var walletAddress: String = ""
        var walletBalance: String = ""
        val output = findViewById<TextView>(R.id.output)
        val walletRef = storageRef.child("wallets/${user?.uid}/name")
        walletRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes ->
            walletName = String(bytes)
            val walletRef = storageRef.child("wallets/${user?.uid}/address")
            walletRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes ->
                walletAddress = String(bytes)
                val walletRef = storageRef.child("wallets/${user?.uid}/balance")
                walletRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes ->
                    walletBalance = String(bytes)
                    output.text =
                        "Wallet Name: $walletName Wallet Address: $walletAddress Wallet Balance: $walletBalance"
                }.addOnFailureListener {
                    // Handle any errors
                }
                }
    }}
}