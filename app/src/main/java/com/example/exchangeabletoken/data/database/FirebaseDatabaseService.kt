package com.example.exchangeabletoken.data.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDatabaseService {

    fun checkUserExists(name: String, onResult: (Boolean) -> Unit) {
        val rootRef = Firebase.database.reference

        rootRef.child("users").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var userExists = false

                snapshot.children.forEach {
                    if (it.child("name").value == name) {
                        userExists = true
                        return@forEach
                    }
                }

                onResult(userExists)
            }

            override fun onCancelled(error: DatabaseError) {
                // Do nothing
            }
        })
    }
}