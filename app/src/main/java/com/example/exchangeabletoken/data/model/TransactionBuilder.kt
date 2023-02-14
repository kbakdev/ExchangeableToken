package com.example.exchangeabletoken.data.model

import com.google.firebase.auth.FirebaseAuth
import java.sql.Timestamp
import kotlin.properties.Delegates

class TransactionBuilder {
    private var amount by Delegates.notNull<Double>()
    private lateinit var receiver: String
    private lateinit var description: String

    fun setAmount(amount: Double): TransactionBuilder {
        this.amount = amount
        return this
    }

    fun setReceiver(receiver: String): TransactionBuilder {
        this.receiver = receiver
        return this
    }

    fun setDescription(description: String): TransactionBuilder {
        this.description = description
        return this
    }

    fun build(): Transaction {
        // get current user
        val user = FirebaseAuth.getInstance().currentUser

        // get current time
        val timestamp = Timestamp(System.currentTimeMillis())

        // create transaction
        return Transaction(amount, receiver, user!!.uid, description, timestamp)
    }
}