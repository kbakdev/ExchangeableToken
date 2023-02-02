package com.example.exchangeabletoken

import java.sql.Timestamp

class Transaction(
<<<<<<< HEAD
    var amount: Double,
=======
    var amount: String,
>>>>>>> d6b29e1 (feat: app minor upgrade)
    var receiver: String,
    var sender: String,
    var description: String,
    var timestamp: Timestamp,
<<<<<<< HEAD
)
=======
) {
    constructor() : this("", "", "", "", Timestamp(System.currentTimeMillis()))
}
>>>>>>> d6b29e1 (feat: app minor upgrade)
