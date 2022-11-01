package com.example.exchangeabletoken

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DataBaseService {
    companion object {
        fun createProduct() {
            FirebaseDatabase.getMarketData().forEach {
                Firebase.database.reference.child("products").child(it.id.toString()).setValue(it)
            }
        }
    }
}
