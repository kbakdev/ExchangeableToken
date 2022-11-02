package com.example.exchangeabletoken

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(product: DataProduct): DataProduct {
        return product
    }

}
