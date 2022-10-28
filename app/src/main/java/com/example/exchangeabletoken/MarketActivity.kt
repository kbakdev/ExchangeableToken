package com.example.exchangeabletoken

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MarketActivity : AppCompatActivity() {
    val size: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_activity)

        // Handle log out button
//        val logoutButton = findViewById<Button>(R.id.logoutButton)
//        logoutButton.setOnClickListener {
//            Firebase.auth.signOut()
//            finish()
//        }
        val walletButton = findViewById<FloatingActionButton>(R.id.walletButton)
        walletButton.setOnClickListener {
            // pop up a dialog with wallet
        }
    }
    operator fun get(position: Int): CharSequence? {
        return MarketService.getMarketData()[position]
    }
}

private operator fun Unit.get(position: Int): CharSequence? {
    return MarketService.getMarketData()[position]
}

