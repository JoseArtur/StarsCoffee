package com.example.starscoffee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starscoffee.adapters.CartListAdapter
import com.example.starscoffee.dialogs.CouponDialog
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Coupon
import com.example.starscoffee.models.Foods
import com.example.starscoffee.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCartData()

        binding.buttonReviewAddress.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        binding.textViewApplyVoucher.setOnClickListener {
            val couponDialog = CouponDialog(getCouponList(), couponClickListener)
            couponDialog.show(supportFragmentManager, "payment")
        }
    }

    private fun getCouponList(): MutableList<Coupon> {
        val couponList: MutableList<Coupon> = mutableListOf()
        couponList.add(
            Coupon(
                0,
                "hwrghg",
                "120 discount on first order!",
                120,
                "15 Aug, 2022",
                true
            )
        )
        couponList.add(
            Coupon(
                0,
                "DINNER60",
                "60 Taka off on order from 7pm to 11 pm!",
                60,
                "21 Aug, 2022",
                true
            )
        )
        couponList.add(
            Coupon(
                0,
                "WELCOME10",
                "10% discount on old account holders!",
                200,
                "18 Aug, 2022",
                false
            )
        )

        return couponList
    }

    private fun setupCartData() {
        val foodList: MutableList<Foods> = mutableListOf()

        binding.recyclerCartItem.setHasFixedSize(true)
        binding.recyclerCartItem.isNestedScrollingEnabled = false
        binding.recyclerCartItem.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val cartListAdapter = CartListAdapter(this, foodList, cartClickListener)
        binding.recyclerCartItem.adapter = cartListAdapter
    }

    private val cartClickListener: ClickListener<Foods> = object :
        ClickListener<Foods> {
        override fun onClicked(data: Foods) {
        }

    }

    private val couponClickListener: ClickListener<Coupon> = object :
        ClickListener<Coupon> {
        override fun onClicked(data: Coupon) {
            Toast.makeText(this@CartActivity, data.couponCode, Toast.LENGTH_SHORT).show()
        }

    }
}