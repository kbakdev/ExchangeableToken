package com.example.exchangeabletoken

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.method.DialerKeyListener.CHARACTERS
import java.net.URL
import java.util.*

class FirebaseDatabase {
    companion object {
        fun getMarketData(): List<DataProduct> {1
            return listOf(
                DataProduct(
                    generateRandomId(),
                    generateRandomString(),
                    generateRandomPrice(),
                    createRandomBitmap(),
                    generateRandomString()
                ),
            )
        }

        private fun generateRandomId(): Int {
            return (0..100000).random()
        }

        private fun generateRandomString(): String {
            val randomName = StringBuilder()
            val characters = CHARACTERS
            val random = Random()
            while (randomName.length < 2) {
                val index = random.nextInt(characters.size)
                randomName.append(characters[index])
            }
            return randomName.toString().lowercase()
        }

        private fun generateRandomPrice(): Int {
            val random = Random()
            return random.nextInt(100)
        }

        // create randomized mock bitmap
        private fun createRandomBitmap(): Bitmap {
            val random = Random()
            val width = random.nextInt(1000)
            val height = random.nextInt(1000)
            return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }
    }
}
