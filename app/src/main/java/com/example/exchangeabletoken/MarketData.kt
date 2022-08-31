package com.example.exchangeabletoken

// MarketData is the data that is returned from the exchange
data class MarketData(
    val exchange: String,
    val base: String,
    val quote: String,
    val price: Double,
    val volume: Double,
    val timestamp: Long
)
