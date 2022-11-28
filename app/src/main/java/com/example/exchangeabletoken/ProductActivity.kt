package com.example.exchangeabletoken

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.exchangeabletoken.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_product)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener {
            DataBaseService.createProduct()
        }

        binding.mockMarketDataButton.setOnClickListener {
            DataBaseService.mockMarketData()
        }

        // add product handle button
        binding.addProduct.setOnClickListener {
            // validate input
            if (binding.productName.text.toString().isNotEmpty() && binding.price.text.toString().isNotEmpty() && binding.productImage.text.toString().isNotEmpty() && binding.category.text.toString().isNotEmpty()) {
                // add product
                DataBaseService.addProduct(binding.productName.text.toString(), binding.price.text.toString().toInt(), binding.productImage.text.toString(), binding.category.text.toString())
                // clear input
                binding.productName.text.clear()
                binding.price.text.clear()
                binding.productImage.text.clear()
                binding.category.text.clear()
                // show success message
                Snackbar.make(binding.root, "Product added successfully", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(it, "Please fill all input", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_product)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}