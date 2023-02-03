package com.example.exchangeabletoken

import android.graphics.Bitmap

data class DataProduct(
    val id: Int,
    val name: String,
    val price: Int,
    val image: Bitmap,
    val category: String
)
