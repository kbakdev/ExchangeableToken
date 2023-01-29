package com.example.exchangeabletoken

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MarketActivity : AppCompatActivity() {
    val size: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_activity)
        // set current user name for UI element with id "current_user_name" if user is logged in
        if (Firebase.auth.currentUser != null) {
            val currentUser = Firebase.auth.currentUser
            val currentUserName = findViewById<TextView>(R.id.current_user_name)
            "Hello, ${currentUser?.email}!".also { currentUserName.text = it }
        }

        // set current balance for UI element with id "current_balance" if user is logged in
        if (Firebase.auth.currentUser != null) {
            val currentUser = Firebase.auth.currentUser
            val currentBalance = findViewById<TextView>(R.id.user_info)
            val database = Firebase.database
            val myRef = database.getReference("users/${currentUser?.uid}")

            // get address, email, name, phone, uid, and balance from realtime database of current user
            val address = findViewById<TextView>(R.id.address)
            val email = findViewById<TextView>(R.id.email)
            val name = findViewById<TextView>(R.id.name)
            val phone = findViewById<TextView>(R.id.phone)
            val uid = findViewById<TextView>(R.id.uid)
            val balance = findViewById<TextView>(R.id.balance)

            // set address, email, name, phone, uid, and balance for UI elements with id "address", "email", "name", "phone", "uid", and "balance" if user is logged in
            myRef.get().addOnSuccessListener {
                address.text = it.child("address").value.toString()
                email.text = it.child("email").value.toString()
                name.text = it.child("name").value.toString()
                phone.text = it.child("phone").value.toString()
                uid.text = it.child("uid").value.toString()
                balance.text = it.child("balance").value.toString()
            }
        }

//         Handle log out button
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            finish()
        }
        val walletButton = findViewById<FloatingActionButton>(R.id.wallet_button)
        walletButton.setOnClickListener {
            val intent = Intent(this, WalletStatusActivity::class.java)
            startActivity(intent)
        }
        // handle settings button
        val settingsButton = findViewById<FloatingActionButton>(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        // handle product button
        val productButton = findViewById<FloatingActionButton>(R.id.product_button)
        productButton.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }
        // handle search product button
        val searchProductButton = findViewById<FloatingActionButton>(R.id.search_product_button)
        searchProductButton.setOnClickListener {
            val intent = Intent(this, SearchProductActivity::class.java)
            startActivity(intent)
        }
        // handle transactions button
        val transactionButton = findViewById<FloatingActionButton>(R.id.transactions_button)
        transactionButton.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
    }
    operator fun get(position: Int): CharSequence? {
        return MarketService.getMarketData()[position]
    }
}

private operator fun Unit.get(position: Int): CharSequence? {
    return MarketService.getMarketData()[position]
}

