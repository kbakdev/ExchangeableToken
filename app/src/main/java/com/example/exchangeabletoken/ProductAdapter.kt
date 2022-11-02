package com.example.exchangeabletoken

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(productsByCategory: List<DataProduct>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val productsByCategory: List<DataProduct> = productsByCategory
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = productsByCategory[position]
        (holder as ProductViewHolder).bind(product)
    }
    override fun getItemCount(): Int {
        return productsByCategory.size
    }
}
