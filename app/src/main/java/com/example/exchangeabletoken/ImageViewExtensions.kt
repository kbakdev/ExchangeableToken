package com.example.exchangeabletoken

import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ImageViewExtensions {
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())
    fun loadImage(url: String, imageView: ImageView) {
        executor.execute {
            val bitmap = BitmapFactory.decodeStream(java.net.URL(url).openStream())
            handler.post { imageView.setImageBitmap(bitmap) }
        }
    }
}

/**
 * This is a convenience method that asynchronously loads an image
 * from a given URL into an ImageView.
 *
 * @param url The URL where the picture is located.
 */
fun ImageView.loadImage(url: String) {
    val imageViewExtensions = ImageViewExtensions()
    imageViewExtensions.loadImage(url, this)
    
    val executor = Executors.newSingleThreadExecutor()
    val handler = Handler(Looper.getMainLooper())
    
    executor.execute {
        try {
            val bitmap = BitmapFactory.decodeStream(java.net.URL(url).openStream())
            handler.post { this.setImageBitmap(bitmap) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}