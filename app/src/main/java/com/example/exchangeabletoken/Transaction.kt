package com.example.exchangeabletoken

class Transaction {
    var amount: String? = null
    var receiver: String? = null
    var sender: String? = null
    var description: String? = null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(amount: String?, receiver: String?, sender: String?, description: String?) {
        this.amount = amount
        this.receiver = receiver
        this.sender = sender
        this.description = description
    }

    override fun toString(): String {
        return "Transaction(amount=$amount, receiver=$receiver, sender=$sender, description=$description)"
    }
}