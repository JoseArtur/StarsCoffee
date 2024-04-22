package com.example.starscoffee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.starscoffee.adapters.FoodsViewPagerAdapter
import com.example.starscoffee.fragments.VoucherListFragment
import com.example.starscoffee.databinding.ActivityVoucherBinding

class VoucherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoucherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoucherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val api = StarsCoffeeAPI()

        getCategory()
    }

    private fun getCategory() {
        val categories: MutableList<String> = mutableListOf()
        categories.add("Vouchers")

        binding.viewPager?.let { setupViewPager(it, categories) }
//        tabLayout!!.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager, list: List<String>) {
        val viewPagerAdapter = FoodsViewPagerAdapter(supportFragmentManager)


        for (item in list){
            val fragment = VoucherListFragment()
            viewPagerAdapter!!.addFragment(fragment, item)
        }

        viewPager.adapter = viewPagerAdapter
        binding.tabLayout!!.setupWithViewPager(viewPager)
    }
}