package com.example.exchangeabletoken

import android.text.method.DialerKeyListener.CHARACTERS
import java.util.*

class FirebaseDatabase {
    companion object {
        fun getMarketData(): List<DataProduct> {
            return listOf(
                DataProduct(
                    generateRandomId(),
                    generateRandomString(),
                    generateRandomPrice(),
                    generateRandomUrlWithImage(),
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
            return randomName.toString()
        }

        private fun generateRandomPrice(): Int {
            val random = Random()
            return random.nextInt(100)
        }

        private fun generateRandomUrlWithImage(): String {
            val random = Random()
            val randomUrl = StringBuilder()
            val characters = CHARACTERS
            while (randomUrl.length < 10) {
                val index = random.nextInt(characters.size)
                randomUrl.append(characters[index])
            }
            return "https://picsum.photos/200/300?random=$randomUrl"
        }
    }
}
