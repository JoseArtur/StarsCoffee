package com.evanemran.quickfoods
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.evanemran.quickfoods.adapters.*
import com.evanemran.quickfoods.dialogs.PaymentOptionDialog
import com.evanemran.quickfoods.dialogs.PermissionDialog
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.*
import com.google.android.material.navigation.NavigationView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_grids.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    var drawer: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPoints = "10"
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        askPermission()

        setupServices()
      //  setupRecents()
        setupDeals()
    //    setupCuisine()
        setupNavMenu()

        setupPointsMenu(userPoints)

        imageButton_cart.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }
        pointsButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }

    }

    private fun askPermission() {
        if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissionDialog = PermissionDialog(permissionListener)
            permissionDialog.show(supportFragmentManager, "permission")
        }

        /*ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            1
        )*/
    }
private var apiService :CoffeeAPIService
    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://stars-coffee-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        apiService = retrofit.create(CoffeeAPIService::class.java)
    }
/*    private fun setupRecents() {
        apiService.getMenuItems().enqueue(object : Callback<List<Foods>> {
            override fun onResponse(call: Call<List<Foods>>, response: Response<List<Foods>>) {
                if (response.isSuccessful) {
                    val recents = response.body()?.map { item ->
                        Foods(
                            item.id,
                            item.foodName,
                            item.description,
                            item.price,
                            item.image_url
                        )
                    }
                    recycler_recents.setHasFixedSize(true)
                    recycler_recents.isNestedScrollingEnabled = true
                    recycler_recents.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL)
                    val recentsAdapter =
                        recents?.let {
                            RecentsAdapter(this@MainActivity,
                                it, restaurantClickListener)
                        }
                    recycler_recents.adapter = recentsAdapter
                } else {

                    // log error
                    // Handle the case where the response is not successful
                    println("Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Foods>>, t: Throwable) {
                println("Error: ${t.message}")
                // Handle the case where the request failed
            }
        })
    }*/

    private fun setupNavMenu() {
        val navMenus: MutableList<DrawerMenu> = mutableListOf()
        navMenus.add(DrawerMenu.FAVORITES)
        navMenus.add(DrawerMenu.ORDER_REORDER)
        navMenus.add(DrawerMenu.VOUCHERS)
        navMenus.add(DrawerMenu.LOGOUT)

        recycler_nav.setHasFixedSize(true)
        recycler_nav.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val drawerAdapter = DrawerAdapter(this, navMenus, drawerClickListener)
        recycler_nav.adapter = drawerAdapter
    }

    private fun setupPointsMenu(points: String) {
        pointsButton.run { pointsButton.setText(points) }
    }

/*    private fun setupCuisine() {
        val cuisines: MutableList<Cuisine> = mutableListOf()
        cuisines.add(Cuisine(0, R.drawable.cuisine, "Fast food"))
        cuisines.add(Cuisine(1, R.drawable.cuisine_dessert, "Dessert"))
        cuisines.add(Cuisine(2, R.drawable.cuisine_asian, "Asian"))
        cuisines.add(Cuisine(3, R.drawable.cuisine_bangladeshi, "Bangladeshi"))
        cuisines.add(Cuisine(4, R.drawable.cuisine_italian, "Italian"))
        cuisines.add(Cuisine(5, R.drawable.cuisine_chinese, "Chinese"))
        cuisines.add(Cuisine(6, R.drawable.cuisine_indian, "Indian"))
        cuisines.add(Cuisine(6, R.drawable.cuisine_japanese, "Japanese"))
        cuisines.add(Cuisine(6, R.drawable.cuisine_beverages, "Beverage"))
        cuisines.add(Cuisine(6, R.drawable.cuisine_american, "American"))

        recycler_cuisine.setHasFixedSize(true)
        recycler_cuisine.isNestedScrollingEnabled = false
        recycler_cuisine.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL)
        val cuisineAdapter = CuisineAdapter(this, cuisines, cuisineClickListener)
        recycler_cuisine.adapter = cuisineAdapter
    }*/

    private fun setupDeals() {
        val deals: MutableList<Deals> = mutableListOf()
        deals.add(Deals(0, R.drawable.deals))
        deals.add(Deals(1, R.drawable.deals))
        deals.add(Deals(2, R.drawable.deals))
        deals.add(Deals(3, R.drawable.deals))
        deals.add(Deals(4, R.drawable.deals))
        deals.add(Deals(5, R.drawable.deals))
        deals.add(Deals(6, R.drawable.deals))

        recycler_deals.setHasFixedSize(true)
        recycler_deals.isNestedScrollingEnabled = false
        recycler_deals.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dealsAdapter = DealsAdapter(this, deals, dealsClickListener)
        recycler_deals.adapter = dealsAdapter
    }

    private fun setupServices() {
        val services: MutableList<Service> = mutableListOf()
        services.add(Service.ITEMS)
        recycler_service.setHasFixedSize(true)
        recycler_service.isNestedScrollingEnabled = false
        recycler_service.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        val serviceAdapter = ServiceAdapter(this, services, servicesClickListener)
        recycler_service.adapter = serviceAdapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    private val servicesClickListener: ClickListener<Service> = object : ClickListener<Service>{
        override fun onClicked(data: Service) {
            startActivity(Intent(this@MainActivity, RestaurantDetailActivity::class.java))
        }
    }

    private val dealsClickListener: ClickListener<Deals> = object : ClickListener<Deals>{
        override fun onClicked(data: Deals) {
            Toast.makeText(this@MainActivity, "Clicked!", Toast.LENGTH_SHORT).show()
        }

    }

    private val restaurantClickListener: ClickListener<Foods> = object : ClickListener<Foods> {
        override fun onClicked(data: Foods) {
            startActivity(Intent(this@MainActivity, FoodDetailActivity::class.java))
        }

    }

    private val cuisineClickListener: ClickListener<Cuisine> = object : ClickListener<Cuisine>{
        override fun onClicked(data: Cuisine) {
            startActivity(Intent(this@MainActivity, RestaurantsActivity::class.java))
        }

    }

    private val drawerClickListener: ClickListener<DrawerMenu> = object : ClickListener<DrawerMenu>{
        override fun onClicked(data: DrawerMenu) {
            Toast.makeText(this@MainActivity, "Clicked " + data.title, Toast.LENGTH_SHORT).show()
        }

    }

    private val permissionListener: ClickListener<Boolean> = object : ClickListener<Boolean>{
        override fun onClicked(data: Boolean) {
//            Toast.makeText(this@MainActivity, "Clicked " + data.title, Toast.LENGTH_SHORT).show()
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                askPermission()
            }
        }

    }
}