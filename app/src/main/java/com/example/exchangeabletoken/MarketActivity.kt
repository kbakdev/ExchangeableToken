package com.example.exchangeabletoken

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// MarketActivity - used to display the market data
// Use CustomAdapter to display the market data
class MarketActivity : AppCompatActivity() {
    val size: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_activity)
        // Use CustomAdapter to display the market data
        val adapter = CustomAdapter(this, MarketService.getMarketData())
        val listView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.listView)
        listView.adapter = adapter
    }

    operator fun get(position: Int): CharSequence? {
        return MarketService.getMarketData()[position]
    }
}

private operator fun Unit.get(position: Int): CharSequence? {
    return MarketService.getMarketData()[position]
}
