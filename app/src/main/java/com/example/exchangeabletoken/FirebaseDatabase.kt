package com.example.exchangeabletoken

class FirebaseDatabase {
    companion object {
        fun getMarketData(): List<DataProduct> {
            return listOf(
                DataProduct(1, "Product 1", 100, "https://picsum.photos/200/300"),
                DataProduct(2, "Product 2", 200, "https://picsum.photos/200/300"),
                DataProduct(3, "Product 3", 300, "https://picsum.photos/200/300"),
                DataProduct(4, "Product 4", 400, "https://picsum.photos/200/300"),
                DataProduct(5, "Product 5", 500, "https://picsum.photos/200/300"),
                DataProduct(6, "Product 6", 600, "https://picsum.photos/200/300"),
                DataProduct(7, "Product 7", 700, "https://picsum.photos/200/300"),
                DataProduct(8, "Product 8", 800, "https://picsum.photos/200/300"),
                DataProduct(9, "Product 9", 900, "https://picsum.photos/200/300"),
                DataProduct(10, "Product 10", 1000, "https://picsum.photos/200/300"),
                DataProduct(11, "Product 11", 1100, "https://picsum.photos/200/300"),
                DataProduct(12, "Product 12", 1200, "https://picsum.photos/200/300"),
                DataProduct(13, "Product 13", 1300, "https://picsum.photos/200/300"),
                DataProduct(14, "Product 14", 1400, "https://picsum.photos/200/300"),
                DataProduct(15, "Product 15", 1500, "https://picsum.photos/200/300"),
                DataProduct(16, "Product 16", 1600, "https://picsum.photos/200/300"),
                DataProduct(17, "Product 17", 1700, "https://picsum.photos/200/300"),
                DataProduct(18, "Product 18", 1800, "https://picsum.photos/200/300"),
            )
        }
    }
}
