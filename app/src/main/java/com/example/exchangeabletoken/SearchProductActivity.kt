package com.example.exchangeabletoken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class SearchProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)
        // handle search product button
        val searchProductButton = findViewById<FloatingActionButton>(R.id.search_product_button)
        searchProductButton.setOnClickListener {
            // get the products by category from database
            // get input from field
            val phrase = findViewById<EditText>(R.id.search_product_by_category_field)
            val phraseText = phrase.text.toString()
            // if the input is empty, show all products
            if (phraseText == "") {
                Snackbar.make(it, "Please enter a category", Snackbar.LENGTH_LONG).show()
            } else {
                // if the input is not empty, return the products by category
                println("The phrase is: $phraseText")
                println("The products by category are: ")
                // get the products by category from database
                DataBaseService.getProductsByCategory(phraseText)
                println("output from database: ${DataBaseService.getProductsByCategory(phraseText)}")

                // get recyclerview element
                val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.product_recycler_view)
                // set layout manager
                recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
                // show data in recyclerview
                recyclerView.adapter = ProductAdapter(DataBaseService.getProductsByCategory(phraseText))
            }
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, MarketActivity::class.java)
        startActivity(intent)
    }
}