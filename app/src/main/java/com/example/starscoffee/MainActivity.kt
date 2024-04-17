package com.example.starscoffee

import ApiTest
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.starscoffee.VoucherActivity
import com.example.starscoffee.adapters.DealsAdapter
import com.example.starscoffee.adapters.DrawerAdapter
import com.example.starscoffee.adapters.ServiceAdapter
import com.example.starscoffee.databinding.ActivityMainBinding
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Deals
import com.example.starscoffee.models.DrawerMenu
import com.example.starscoffee.models.Service
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val userPoints = "10"

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

        setupPointsMenu(userPoints)

        binding.pointsButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }

        binding.imageButtonCart.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }

        val api = ApiTest()
        api.testRun()

        println("Here we are")
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

    private fun setupPointsMenu(points: String) {
        binding.pointsButton.run { binding.pointsButton.setText(points) }
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
                when (data) {
                    DrawerMenu.ORDER_REORDER -> {
                        // Start OrderHistoryActivity when ORDER_REORDER is clicked
                        val intent = Intent(this@MainActivity, OrderHistoryActivity::class.java)
                        startActivity(intent)
                    }
                    DrawerMenu.VOUCHERS -> {

                        // Start OrderHistoryActivity when ORDER_REORDER is clicked

                        val intent = Intent(this@MainActivity, VoucherActivity::class.java)
                        startActivity(intent)
                    }

                    else -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Clicked " + data.title,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }

}
