package com.example.starscoffee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.starscoffee.adapters.FoodsViewPagerAdapter
import com.example.starscoffee.fragments.FoodListFragment
import com.example.starscoffee.models.Foods
import com.example.starscoffee.models.Restaurant
import com.google.android.material.tabs.TabLayout
import com.example.starscoffee.databinding.ActivityRestaurantDetailBinding

class RestaurantDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCategory()
    }

    private fun getCategory() {
        val categories: MutableList<String> = mutableListOf()
        categories.add("Items")

        binding.viewPager.let { setupViewPager(it, categories) }
    }

    private fun setupViewPager(viewPager: ViewPager, list: List<String>) {
        val viewPagerAdapter = FoodsViewPagerAdapter(supportFragmentManager)

        for (item in list){
            val fragment = FoodListFragment()
            viewPagerAdapter.addFragment(fragment, item)
        }

        viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(viewPager)
    }
}