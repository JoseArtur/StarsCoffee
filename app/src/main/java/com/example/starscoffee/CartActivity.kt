package com.example.starscoffee

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starscoffee.adapters.CartListAdapter
import com.example.starscoffee.databinding.ActivityCartBinding
import com.example.starscoffee.dialogs.CouponDialog
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Coupon
import com.example.starscoffee.models.Foods

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding

    companion object {
        val cartList: MutableList<Foods> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCartData()

        binding.buttonReviewAddress.setOnClickListener {
            startActivity(
                Intent(this, CheckoutActivity::class.java).putExtra(
                    "cartList",
                    ArrayList(cartList)
                ).putExtra("total", binding.textViewTotal.text.toString())
                    .putExtra("subTotal", binding.textViewSubTotal.text.toString())
            )
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
                1,
                "15 Aug, 2022",
                true
            )
        )
        couponList.add(
            Coupon(
                0,
                "DINNER60",
                "60 Taka off on order from 7pm to 11 pm!",
                2,
                "21 Aug, 2022",
                true
            )
        )
        couponList.add(
            Coupon(
                0,
                "WELCOME10",
                "10% discount on old account holders!",
                1,
                "18 Aug, 2022",
                false
            )
        )

        return couponList
    }

    private fun setupCartData() {
        binding.recyclerCartItem.setHasFixedSize(true)
        binding.recyclerCartItem.isNestedScrollingEnabled = false
        binding.recyclerCartItem.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val cartListAdapter = CartListAdapter(this, CartActivity.cartList, cartClickListener)
        binding.recyclerCartItem.adapter = cartListAdapter
        calculateSubTotal()
        calcutateTotal()

    }

    // Function to calculate the total
    fun calcutateTotal() {
        
        val total = cartList.sumOf { it.price * it.quantity }
        val textViewTotal = findViewById<TextView>(R.id.textView_total)
        textViewTotal.text = total.toString()
    }

    // Function to calculate the subtotal
    fun calculateSubTotal() {
        val subTotal = cartList.sumOf { it.price * it.quantity }
        val textViewSubTotal = findViewById<TextView>(R.id.textView_subTotal)
        textViewSubTotal.text = subTotal.toString()
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

            // Calculate the new subtotal after applying the coupon
            val total = cartList.sumOf { it.price * it.quantity } - data.couponAmt
            val textViewTotal = findViewById<TextView>(R.id.textView_total)
            textViewTotal.text = getString(R.string.total_price, total)
        }
    }
}