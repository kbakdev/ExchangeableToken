package com.example.exchangeabletoken

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DataBaseService {
    fun addUser(uid: String, name: String, email: String, phone: String, address: String) {
        val database = Firebase.database
        val myRef = database.getReference("users")
        myRef.child(name).child("name").setValue(name)
        myRef.child(name).child("uid").setValue(uid)
        myRef.child(name).child("email").setValue(email)
        myRef.child(name).child("phone").setValue(phone)
        myRef.child(name).child("address").setValue(address)
    }

    companion object {
        fun createProduct() {
            FirebaseDatabase.getMarketData().forEach {
                Firebase.database.reference.child("products").child(it.id.toString()).setValue(it)
            }
        }
    }
}
