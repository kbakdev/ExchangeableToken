package com.example.exchangeabletoken

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(product: DataProduct): DataProduct {
        val textView: TextView = itemView.findViewById(R.id.textView)
        return DataProduct(product.id, product.name, product.price, product.image, product.category)
    }
}
