package com.example.starscoffee

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.starscoffee.adapters.DrawerAdapter
import com.example.starscoffee.adapters.FoodsViewPagerAdapter
import com.example.starscoffee.databinding.ActivityMainBinding
import com.example.starscoffee.fragments.FoodListFragment
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.DrawerMenu
import com.example.starscoffee.models.Service
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val points = sharedPreferences.getInt("points", 0)
        val userPoints = points

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

        getCategory()
        setupNavMenu()

        setupPointsMenu(userPoints)

        binding.pointsButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }

        binding.imageButtonCart.setOnClickListener {
            val intent = Intent(this@MainActivity, CartActivity::class.java)
            intent.putExtra("userPoints", userPoints)
            startActivity(intent)
        }

        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        val name = sharedPreferences.getString("name", "NOT DEFINED")

        welcomeTextView.text = "Welcome! $name"
    }


    private fun setupNavMenu() {
        val navMenus: MutableList<DrawerMenu> = mutableListOf()
        //navMenus.add(DrawerMenu.FAVORITES)
        navMenus.add(DrawerMenu.ORDER_REORDER)
        navMenus.add(DrawerMenu.VOUCHERS)
        navMenus.add(DrawerMenu.LOGOUT)


        binding.recyclerNav.setHasFixedSize(true)
        binding.recyclerNav.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val drawerAdapter = DrawerAdapter(this, navMenus, drawerClickListener)
        binding.recyclerNav.adapter = drawerAdapter

    }

    private fun setupPointsMenu(points: Int) {
        binding.pointsButton.run { binding.pointsButton.setText(points.toString()) }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    private val servicesClickListener: ClickListener<Service> = object : ClickListener<Service> {
        override fun onClicked(data: Service) {
            startActivity(Intent(this@MainActivity, RestaurantDetailActivity::class.java))
        }
    }

    private fun getCategory() {
        val categories: MutableList<String> = mutableListOf()
        categories.add("Items")

        binding.viewPager.let { setupViewPager(it, categories) }
    }

    private fun setupViewPager(viewPager: ViewPager, list: List<String>) {
        val viewPagerAdapter = FoodsViewPagerAdapter(supportFragmentManager)

        for (item in list) {
            val fragment = FoodListFragment()
            viewPagerAdapter.addFragment(fragment, item)
        }

        viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(viewPager)
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

                    DrawerMenu.LOGOUT -> {

                        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.clear()
                        editor.apply()
                        // Start OrderHistoryActivity when ORDER_REORDER is clicked

                        val intent = Intent(this@MainActivity, SignupActivity::class.java)
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
