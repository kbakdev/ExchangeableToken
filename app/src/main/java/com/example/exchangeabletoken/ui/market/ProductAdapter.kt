package com.example.exchangeabletoken.ui.market

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeabletoken.R
import com.example.exchangeabletoken.data.model.Product

class ProductAdapter(private val productsByCategory: List<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(productsByCategory[position])
    }
    override fun getItemCount(): Int {
        return productsByCategory.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataProduct: Product) {
            val textView: TextView = itemView.findViewById(R.id.do_transaction)
            textView.text = dataProduct.name
        }

        val textView: TextView = itemView.findViewById(R.id.do_transaction)
    }
}
