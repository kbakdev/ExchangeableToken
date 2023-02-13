package com.example.exchangeabletoken.utils

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ReceiverChecker {
    fun checkReceiver(receiver: String, callback: (Boolean) -> Unit) {
        // get reference to the realtime database
        val database = Firebase.database
        val myRef = database.getReference("users")

        // get user based on the receiver's username
        myRef.orderByChild("name").equalTo(receiver).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // check if user exists
                val userExists = dataSnapshot.exists()

                // invoke the callback function with the result
                callback(userExists)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("checkReceiver", "Error checking receiver: ${databaseError.message}")
            }
        })
    }

    fun getReceiverUid(receiver: String): Unit {

    }
}