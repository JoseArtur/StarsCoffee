package com.evanemran.quickfoods

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.quickfoods.adapters.AddOnsAdapter
import com.evanemran.quickfoods.adapters.DrawerAdapter
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.AddOns
import com.evanemran.quickfoods.models.DrawerMenu
import kotlinx.android.synthetic.main.activity_food_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class FoodDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)
        setAddons()

        button_addToCart.setOnClickListener {
            this.finish()
        }
    }

    private fun setAddons() {
        val addOns: MutableList<AddOns> = mutableListOf()
        addOns.add(AddOns(0, "BBQ Sauce", 30, false))
        addOns.add(AddOns(0, "Cheese", 30, false))
        addOns.add(AddOns(0, "Pepperoni", 30, false))
        addOns.add(AddOns(0, "Mayo", 30, false))

        recycler_addons.setHasFixedSize(true)
        recycler_addons.isNestedScrollingEnabled = false
        recycler_addons.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val addonAdapter = AddOnsAdapter(this, addOns, addOnClickListener)
        recycler_addons.adapter = addonAdapter
    }

    private val addOnClickListener: ClickListener<AddOns> = object :
        ClickListener<AddOns> {
        override fun onClicked(data: AddOns) {
            startActivity(Intent(this@FoodDetailActivity, RestaurantsActivity::class.java))
        }

    }
}