package com.example.starscoffee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starscoffee.adapters.AddOnsAdapter
import com.example.starscoffee.adapters.DrawerAdapter
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.AddOns
import com.example.starscoffee.models.DrawerMenu
import com.example.starscoffee.databinding.ActivityFoodDetailBinding

class FoodDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAddons()

        binding.buttonAddToCart.setOnClickListener {
            this.finish()
        }
    }

    private fun setAddons() {
        val addOns: MutableList<AddOns> = mutableListOf()
        addOns.add(AddOns(0, "BBQ Sauce", 30, false))
        addOns.add(AddOns(0, "Cheese", 30, false))
        addOns.add(AddOns(0, "Pepperoni", 30, false))
        addOns.add(AddOns(0, "Mayo", 30, false))

        binding.recyclerAddons.setHasFixedSize(true)
        binding.recyclerAddons.isNestedScrollingEnabled = false
        binding.recyclerAddons.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val addonAdapter = AddOnsAdapter(this, addOns, addOnClickListener)
        binding.recyclerAddons.adapter = addonAdapter
    }

    private val addOnClickListener: ClickListener<AddOns> = object :
        ClickListener<AddOns> {
        override fun onClicked(data: AddOns) {
            startActivity(Intent(this@FoodDetailActivity, RestaurantDetailActivity::class.java))
        }

    }
}