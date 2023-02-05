package com.example.exchangeabletoken

import android.graphics.Bitmap
import android.text.method.DialerKeyListener.CHARACTERS
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks.forException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*
import java.util.concurrent.TimeoutException

class FirebaseDatabase {
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

        fun addTransaction(transaction: Transaction, receiver: String) {
            // implement transaction mechanism, which checks if receiver really exists, and if sender has enough money
            // get database reference
            val database = com.google.firebase.database.FirebaseDatabase.getInstance()
            val myRef = database.getReference("transactions")
            val user = FirebaseAuth.getInstance().currentUser
            val uid = user?.uid.toString()


            // check if receiver exists
            checkUser(receiver).addOnSuccessListener {
                // check if sender has enough money
                val balance = getBalance(uid).toString().toInt()
                if (balance >= transaction.amount.toInt()) {
                    // add transaction to database
                    val transactionId = myRef.push().key
                    myRef.child(transactionId.toString()).setValue(transaction)
                    // update sender's balance
                    val senderRef = database.getReference("users").child(uid).child("balance")
                    senderRef.setValue(balance - transaction.amount.toInt())
                    // update receiver's balance
                    val receiverRef = database.getReference("users").child(receiver).child("balance")
                    receiverRef.setValue(balance + transaction.amount.toInt())
                }
            }
        }

        fun checkUser(receiver: String): Task<Void> {
            // get database reference
            val database = com.google.firebase.database.FirebaseDatabase.getInstance()
            val usersRef = database.getReference("users")
            val query = usersRef.orderByChild("email").equalTo(receiver)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            }
            )

            return forException(Exception("User does not exist"))
        }

        fun getBalance(uid: String): Any {
            // get database reference
            val database = com.google.firebase.database.FirebaseDatabase.getInstance()
            val myRef = database.getReference("users").child(uid).child("balance")
            var balance = 0
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    balance = dataSnapshot.getValue(Int::class.java)!!
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            }
            )

            return balance
        }

        fun changeBalance(sender: String, receiver: String, toInt: Int) {
            // get database reference
            val database = com.google.firebase.database.FirebaseDatabase.getInstance()
            val senderRef = database.getReference("users").child(sender).child("balance")
            val receiverRef = database.getReference("users").child(receiver).child("balance")
            // if receiver does not exist, return
            checkUser(receiver).addOnSuccessListener {
                return@addOnSuccessListener
            }
            // update sender's balance
            val senderBalance = getBalance(sender).toString().toInt()
            senderRef.setValue(senderBalance - toInt)
            // update receiver's balance
            val receiverBalance = getBalance(receiver).toString().toInt()
            receiverRef.setValue(receiverBalance + toInt)


        }

        fun getUID(receiver: String): String {
            // get database reference
            val database = com.google.firebase.database.FirebaseDatabase.getInstance()
            val myRef = database.getReference("users")
            var uid = ""
            val query = myRef.orderByChild("email").equalTo(receiver)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (data in dataSnapshot.children) {
                        uid = data.key.toString()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            }
            )

            return uid
        }

        fun getInstance(): Any {
            return com.google.firebase.database.FirebaseDatabase.getInstance()
        }
    }
}
