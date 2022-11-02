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

        fun addProduct(name: String, price: Int, image: String, category: String) {
            // get last id
            val lastId = Firebase.database.reference.child("products").push().key
            // add product
            Firebase.database.reference.child("products").child(lastId!!).child("id").setValue(lastId)
            Firebase.database.reference.child("products").child(name).child("name").setValue(name)
            Firebase.database.reference.child("products").child(name).child("price").setValue(price)
            Firebase.database.reference.child("products").child(name).child("image").setValue(image)
            Firebase.database.reference.child("products").child(name).child("category").setValue(category)
        }
    }
}
