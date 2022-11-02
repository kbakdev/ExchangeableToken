package com.example.exchangeabletoken

class FirebaseDatabase {
    companion object {
        fun getMarketData(): List<DataProduct> {
            return listOf(
                DataProduct(1, "Apple", 10, "https://www.pngitem.com/pimgs/m/30-307416_transparent-apple-png-apple-fruit-png-download.png", "Fruit"),
                DataProduct(2, "Orange", 15, "https://www.pngitem.com/pimgs/m/30-307416_transparent-apple-png-apple-fruit-png-download.png", "Fruit"),
                DataProduct(3, "Banana", 20, "https://www.pngitem.com/pimgs/m/30-307416_transparent-apple-png-apple-fruit-png-download.png", "Fruit"),
                DataProduct(4, "Pineapple", 25, "https://www.pngitem.com/pimgs/m/30-307416_transparent-apple-png-apple-fruit-png-download.png", "Fruit"),
                DataProduct(5, "Watermelon", 30, "https://www.pngitem.com/pimgs/m/30-307416_transparent-apple-png-apple-fruit-png-download.png", "Fruit"),
                DataProduct(6, "Mango", 35, "https://www.pngitem.com/pimgs/m/30-307416_transparent-apple-png-apple-fruit-png-download.png", "Fruit"),
            )
        }
    }
}
