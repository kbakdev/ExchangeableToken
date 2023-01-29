package com.example.exchangeabletoken

import android.graphics.Bitmap
import android.text.method.DialerKeyListener.CHARACTERS
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks.forException
import java.util.*

class FirebaseDatabase {
    fun addTransaction(transaction: Transaction) {

        // check if transaction is valid
        if (transaction.amount == null || transaction.receiver == null || transaction.sender == null || transaction.description == null) {
            return
        }

        // check if user that sends the transaction exists
        val sender = getUser(transaction.sender!!)

        // check if user that receives the transaction exists
        val receiver = getUser(transaction.receiver!!)

        // check if sender has enough money
        if (sender.balance < transaction.amount!!.toInt()) {
            return
        }

        val database = com.google.firebase.database.FirebaseDatabase.getInstance()
        val myRef = database.getReference("transactions")
        val id = UUID.randomUUID().toString()
        myRef.child(id).setValue(transaction)
    }

    private fun getUser(sender: String): Balance {
        val database = com.google.firebase.database.FirebaseDatabase.getInstance()
        val myRef = database.getReference("users")
        val user = myRef.child(sender).get()
        return user.result.getValue(Balance::class.java)!!
    }

    companion object {
        fun getMarketData(): List<DataProduct> {
            return listOf(
                DataProduct(
                    generateRandomId(),
                    generateRandomString(),
                    generateRandomPrice(),
                    createRandomBitmap(),
                    generateRandomString()
                ),
            )
        }

        private fun generateRandomId(): Int {
            return (0..100000).random()
        }

        private fun generateRandomString(): String {
            val randomName = StringBuilder()
            val characters = CHARACTERS
            val random = Random()
            while (randomName.length < 2) {
                val index = random.nextInt(characters.size)
                randomName.append(characters[index])
            }
            return randomName.toString().lowercase()
        }

        private fun generateRandomPrice(): Int {
            val random = Random()
            return random.nextInt(100)
        }

        // create randomized mock bitmap
        private fun createRandomBitmap(): Bitmap {
            val random = Random()
            val width = random.nextInt(1000)
            val height = random.nextInt(1000)
            return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }

        fun addTransaction(transaction: Transaction): Task<Void> {
            // get database reference
            val database = com.google.firebase.database.FirebaseDatabase.getInstance()
            val myRef = database.getReference("transactions")
            val id = UUID.randomUUID().toString()
            myRef.child(id).setValue(transaction)
            return forException(Exception("Not implemented"))
        }

    }
}
