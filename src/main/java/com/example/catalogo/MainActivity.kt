package com.example.catalogo

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.catalogo.databinding.ActivityMainBinding
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catalogo.ui.detail.DetailActivity
import com.example.catalogo.ui.home.adapter.ProductAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val recyclerView = binding.rvProducts
        recyclerView.layoutManager = LinearLayoutManager(this)

        val sampleProducts = listOf(
            Product("Producto 1", "Descripción 1", 100.0, "https://cdn-images.farfetch-contents.com/14/28/62/92/14286292_20466860_1000.jpg"),
            Product("Producto 2", "Descripción 2", 200.0, "https://cdn-images.farfetch-contents.com/14/28/62/92/14286292_20466860_1000.jpg"),
            Product("Producto 3", "Descripción 3", 300.0, "https://cdn-images.farfetch-contents.com/14/28/62/92/14286292_20466860_1000.jpg")
        )

        val adapter = ProductAdapter(sampleProducts) { product ->
            // Abre DetailActivity al hacer clic
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("name", product.name)
                putExtra("price", product.price)
                putExtra("description", product.description)
                putExtra("photo", product.photo)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "No me toques!", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}