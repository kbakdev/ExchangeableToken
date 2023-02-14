package com.example.exchangeabletoken.data.model

import com.google.firebase.auth.FirebaseAuth

class User(
    val address : String,
    val balance : Double,
    val email : String,
    val name : String,
    val phone : String,
    val uid : String,
    ) {
    companion object {
        fun isLoggedIn(): Boolean {
            val firebaseAuth = FirebaseAuth.getInstance()
            val currentUser = firebaseAuth.currentUser
            return currentUser != null
        }
    }
}