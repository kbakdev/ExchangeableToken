package com.example.exchangeabletoken.ui.market

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeabletoken.*
import com.example.exchangeabletoken.data.model.User
import com.example.exchangeabletoken.ui.product.ProductActivity
import com.example.exchangeabletoken.ui.search.SearchProductActivity
import com.example.exchangeabletoken.ui.settings.SettingsActivity
import com.example.exchangeabletoken.ui.transaction.TransactionActivity
import com.example.exchangeabletoken.ui.wallet.WalletStatusActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MarketActivity : AppCompatActivity() {
    val size: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_activity)
        // set current user name for UI element with id "current_user_name" if user is logged in
        if (Firebase.auth.currentUser != null) {
            val currentUser = Firebase.auth.currentUser
            val currentUserName = findViewById<TextView>(R.id.current_user_name)
            "Hello, ${currentUser?.email}!".also { currentUserName.text = it }
        }
        //  Handle log out button
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
}

