package com.evanemran.quickfoods

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.evanemran.quickfoods.adapters.FoodsViewPagerAdapter
import com.evanemran.quickfoods.fragments.FoodListFragment
import com.evanemran.quickfoods.fragments.VoucherListFragment
import com.evanemran.quickfoods.models.Foods
import com.evanemran.quickfoods.models.Restaurant
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_restaurant_detail.*

import kotlinx.android.synthetic.main.home_grids.*
class VoucherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)

        getCategory()
//        getFoodList()

    }

    private fun getCategory() {
        val categories: MutableList<String> = mutableListOf()
        categories.add("Vouchers")

        viewPager?.let { setupViewPager(it, categories) }
//        tabLayout!!.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager, list: List<String>) {
        val viewPagerAdapter = FoodsViewPagerAdapter(supportFragmentManager)


        for (item in list){
            val fragment = VoucherListFragment()
            viewPagerAdapter!!.addFragment(fragment, item)
        }

        viewPager.adapter = viewPagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
    }
}