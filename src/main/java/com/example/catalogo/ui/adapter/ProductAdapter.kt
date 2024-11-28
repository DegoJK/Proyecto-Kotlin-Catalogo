package com.example.catalogo.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogo.Product
import com.example.catalogo.R

class ProductAdapter(
    private val productList: List<Product>,
    private val onItemClick: (Product) -> Unit // Listener de clics
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ViewHolder para enlazar las vistas
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tvName)
        val productPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    // Crea el ViewHolder a partir del layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_layout, parent, false)
        return ProductViewHolder(view)
    }

    // Enlaza los datos con las vistas del ViewHolder
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.name
        holder.productPrice.text = "$${product.price}"


        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }


    // Devuelve el tamaño de la lista
    override fun getItemCount(): Int = productList.size
}
