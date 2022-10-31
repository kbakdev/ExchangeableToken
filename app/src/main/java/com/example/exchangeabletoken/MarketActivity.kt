package com.example.exchangeabletoken

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MarketActivity : AppCompatActivity() {
    val size: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_activity)

//         Handle log out button
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            finish()
        }
        val walletButton = findViewById<FloatingActionButton>(R.id.walletButton)
        walletButton.setOnClickListener {
            // pop up a dialog with wallet
        }
        // handle settings button
        val settingsButton = findViewById<FloatingActionButton>(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
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

