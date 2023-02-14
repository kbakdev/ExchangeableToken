package com.example.exchangeabletoken.data.model

import java.sql.Timestamp

class Transaction(
    var amount: Double,
    var receiver: String,
    var sender: String,
    var description: String,
    var timestamp: Timestamp,
)