package com.example.exchangeabletoken

import java.sql.Timestamp

class Transaction(
    var amount: Double,
    var receiver: String,
    var sender: String,
    var description: String,
    var timestamp: Timestamp,
)