package com.example.exchangeabletoken

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// MarketActivity - used to display the market data
// Use CustomAdapter to display the market data
class MarketActivity : AppCompatActivity() {
    val size: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_activity)

        // Handle log out button
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            finish()
        }
    }

    operator fun get(position: Int): CharSequence? {
        return MarketService.getMarketData()[position]
    }
}

private operator fun Unit.get(position: Int): CharSequence? {
    return MarketService.getMarketData()[position]
}

