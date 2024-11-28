package com.example.catalogo.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catalogo.R
import com.example.catalogo.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Obtén los datos del intent
        val photoUrl = intent.getStringExtra("photo") ?: ""
        val name = intent.getStringExtra("name") ?: "Producto desconocido"
        val price = intent.getDoubleExtra("price", 0.0)
        val description = intent.getStringExtra("description") ?: "Sin descripción"


        // Configura los datos en las vistas
        binding.tvDetailName.text = name
        binding.tvDetailPrice.text = "$$price"
        binding.tvDetailDescription.text = description
    }

}
