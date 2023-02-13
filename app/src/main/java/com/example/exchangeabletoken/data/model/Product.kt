package com.example.exchangeabletoken.data.model

import android.graphics.Bitmap

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val image: Bitmap,
    val category: String
)
