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

        fun addTransaction(transaction: Transaction): Task<Void> {
            // get database reference
            val database = com.google.firebase.database.FirebaseDatabase.getInstance()
            val myRef = database.getReference("transactions")
            val id = UUID.randomUUID().toString()

            // set sender as current logged user
            transaction.sender = FirebaseAuth.getInstance().currentUser?.email.toString()

            // check if receiver really exists by email
            val receiver = transaction.receiver
            val usersRef = database.getReference("users")
            val query = usersRef.orderByChild("email").equalTo(receiver)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // user exists
                        // add transaction to database
                        myRef.child(id).setValue(transaction)
                    } else {
                        // user does not exist
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            }
            )

            // save to realtime database


            return forException(Exception("User does not exist"))
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
    }
}
