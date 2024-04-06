package com.evanemran.quickfoods

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.quickfoods.adapters.CartListAdapter
import com.evanemran.quickfoods.dialogs.CouponDialog
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.Coupon
import com.evanemran.quickfoods.models.Foods
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.home_grids.*

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        setupCartData()

        button_reviewAddress.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        textView_applyVoucher.setOnClickListener {
            val couponDialog = CouponDialog(getCouponList(), couponClickListener)
            couponDialog.show(supportFragmentManager, "payment")
        }
    }

    private fun getCouponList(): MutableList<Coupon> {
        val couponList: MutableList<Coupon> = mutableListOf()
        couponList.add(Coupon(0, "hwrghg", "120 discount on first order!", 120, "15 Aug, 2022", true))
        couponList.add(Coupon(0, "DINNER60", "60 Taka off on order from 7pm to 11 pm!", 60, "21 Aug, 2022", true))
        couponList.add(Coupon(0, "WELCOME10", "10% discount on old account holders!", 200, "18 Aug, 2022", false))

        return couponList
    }

    private fun setupCartData() {
        val foodList: MutableList<Foods> = mutableListOf()

        recycler_cartItem.setHasFixedSize(true)
        recycler_cartItem.isNestedScrollingEnabled = false
        recycler_cartItem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val cartListAdapter = CartListAdapter(this, foodList, cartClickListener)
        recycler_cartItem.adapter = cartListAdapter
    }

    private val cartClickListener: ClickListener<Foods> = object :
        ClickListener<Foods> {
        override fun onClicked(data: Foods) {
//            startActivity(Intent(this@CartActivity, RestaurantsActivity::class.java))
        }

    }

    private val couponClickListener: ClickListener<Coupon> = object :
        ClickListener<Coupon> {
        override fun onClicked(data: Coupon) {
//            startActivity(Intent(this@CartActivity, RestaurantsActivity::class.java))
            Toast.makeText(this@CartActivity, data.couponCode, Toast.LENGTH_SHORT).show()
        }

    }
}