package com.example.exchangeabletoken.data.repository

import android.graphics.Bitmap
import android.util.Log
import com.example.exchangeabletoken.data.firebase.FirebaseDatabase
import com.example.exchangeabletoken.utils.FirebaseStorageService
import com.example.exchangeabletoken.data.model.Product
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.ByteArrayOutputStream

class DatabaseService {
    companion object {
        fun mockMarketData() {
            FirebaseDatabase.getMarketData().forEach {
                val database = Firebase.database
                val myRef = database.getReference("products")
                myRef.child(it.name).child("id").setValue(it.id)
                myRef.child(it.name).child("name").setValue(it.name.lowercase())
                myRef.child(it.name).child("price").setValue(it.price)
                myRef.child(it.name).child("category").setValue(it.category.lowercase())

                // create randomized mock bitmap
                val bitmap = it.image

                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

                // add image to firebase
                val storage = FirebaseStorageService.getInstance()
                val storageRef = storage.getReference("images/${it.name}.jpg")
                storageRef.putBytes(baos.toByteArray())
                    .addOnSuccessListener {
                        Log.d("FirebaseStorage", "Image uploaded successfully")
                    }
                    .addOnFailureListener {
                        Log.d("FirebaseStorage", "Image upload failed")
                    }
            }
        }


        fun addProduct(name: String, price: Int, image: Bitmap, category: String) {
            Firebase.database.reference.child("products").child("lastId").get()
                .addOnSuccessListener {
                    // generate random number
                    val id = (0..100000).random()
                    Firebase.database.reference.child("products").child(id.toString()).child("id")
                        .setValue(id)
                    Firebase.database.reference.child("products").child(id.toString()).child("name")
                        .setValue(name.lowercase())
                    Firebase.database.reference.child("products").child(id.toString())
                        .child("price").setValue(price)
                    Firebase.database.reference.child("products").child(id.toString())
                        .child("category").setValue(category.lowercase())
                    // Add to firebase storage image
                    Firebase.database.reference.child("images").child(id.toString()).setValue(image)
                }
                .addOnFailureListener {
                    // Handle any errors
                }
        }

        fun getProductsByCategory(category: String): List<Product> {
            val products = mutableListOf<Product>()
            Firebase.database.reference.child("products").get()
                .addOnSuccessListener {
                    it.children.forEach { product ->
                        if (product.child("category").value.toString() == category) {
                            products.add(
                                Product(
                                    product.child("id").value.toString().toInt(),
                                    product.child("name").value.toString(),
                                    product.child("price").value.toString().toInt(),
                                    // TODO: placeholder
                                    Bitmap.createBitmap(
                                        200,
                                        300,
                                        Bitmap.Config.ARGB_8888
                                    ),
                                    product.child("category").value.toString()
                                )
                            )
                        }
                    }
                }
                .addOnFailureListener {
                    // Handle any errors
                }
            return products
        }
    }
}
