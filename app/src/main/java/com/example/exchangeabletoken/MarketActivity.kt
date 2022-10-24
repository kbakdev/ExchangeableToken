package com.example.exchangeabletoken

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
<<<<<<< HEAD
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
=======
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
>>>>>>> 09e6990 (feat: market and login)
import com.google.firebase.ktx.Firebase

class MarketActivity : AppCompatActivity() {
    val size: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_activity)
<<<<<<< HEAD
        // set current user name for UI element with id "current_user_name" if user is logged in
        if (Firebase.auth.currentUser != null) {
            val currentUser = Firebase.auth.currentUser
            val currentUserName = findViewById<TextView>(R.id.current_user_name)
            "Hello, ${currentUser?.email}!".also { currentUserName.text = it }
=======

        // Handle log out button
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            finish()
        }
    }
>>>>>>> 09e6990 (feat: market and login)

            // get user's information from Firebase realtime database
            val database = Firebase.database
            val myRef = database.getReference("users")
            // show data at user_info element
            val userInfo = findViewById<TextView>(R.id.user_info)
            myRef.child(currentUser?.uid.toString()).get()
                .addOnSuccessListener {
                    // beautify data
                    val data = it.value.toString().replace("{", "").replace("}", "")
                    userInfo.text = data
                }
                .addOnFailureListener {
                    "User not found".also { userInfo.text = it }
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
}

<<<<<<< HEAD
=======
private operator fun Unit.get(position: Int): CharSequence? {
    return MarketService.getMarketData()[position]
}

>>>>>>> 09e6990 (feat: market and login)
