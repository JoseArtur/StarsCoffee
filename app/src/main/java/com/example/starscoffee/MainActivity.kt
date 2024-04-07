package com.example.starscoffee

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.starscoffee.adapters.*
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.*
import com.example.starscoffee.R
import com.example.starscoffee.databinding.ActivityMainBinding
import com.example.starscoffee.databinding.HomeGridsBinding

import com.google.android.material.navigation.NavigationView
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open_nav_drawer,
            R.string.close_nav_drawer
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        setupServices()
        setupDeals()
        setupNavMenu()

        binding.imageButtonCart.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }
    }


    private fun setupNavMenu() {
        val navMenus: MutableList<DrawerMenu> = mutableListOf()
        navMenus.add(DrawerMenu.FAVORITES)
        navMenus.add(DrawerMenu.ORDER_REORDER)
        navMenus.add(DrawerMenu.VOUCHERS)
        navMenus.add(DrawerMenu.LOGOUT)


        binding.recyclerNav.setHasFixedSize(true)
        binding.recyclerNav.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val drawerAdapter = DrawerAdapter(this, navMenus, drawerClickListener)
        binding.recyclerNav.adapter = drawerAdapter

    }


    private fun setupDeals() {
        val deals: MutableList<Deals> = mutableListOf()
        deals.add(Deals(0, R.drawable.deals))
        deals.add(Deals(1, R.drawable.deals))
        deals.add(Deals(2, R.drawable.deals))
        deals.add(Deals(3, R.drawable.deals))
        deals.add(Deals(4, R.drawable.deals))
        deals.add(Deals(5, R.drawable.deals))
        deals.add(Deals(6, R.drawable.deals))

        binding.recyclerDeals.setHasFixedSize(true)
        binding.recyclerDeals.isNestedScrollingEnabled = false
        binding.recyclerDeals.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dealsAdapter = DealsAdapter(this, deals, dealsClickListener)
        binding.recyclerDeals.adapter = dealsAdapter
    }

    private fun setupServices() {
        val services: MutableList<Service> = mutableListOf()
        services.add(Service.ITEMS)
        binding.recyclerService.setHasFixedSize(true)
        binding.recyclerService.isNestedScrollingEnabled = false
        binding.recyclerService.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        val serviceAdapter = ServiceAdapter(this, services, servicesClickListener)
        binding.recyclerService.adapter = serviceAdapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    private val servicesClickListener: ClickListener<Service> = object : ClickListener<Service> {
        override fun onClicked(data: Service) {
            startActivity(Intent(this@MainActivity, RestaurantDetailActivity::class.java))
        }
    }

    private val dealsClickListener: ClickListener<Deals> = object : ClickListener<Deals> {
        override fun onClicked(data: Deals) {
            Toast.makeText(this@MainActivity, "Clicked!", Toast.LENGTH_SHORT).show()
        }

    }

    private val drawerClickListener: ClickListener<DrawerMenu> =
        object : ClickListener<DrawerMenu> {
            override fun onClicked(data: DrawerMenu) {
                Toast.makeText(this@MainActivity, "Clicked " + data.title, Toast.LENGTH_SHORT)
                    .show()
            }

        }


}
