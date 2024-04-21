package com.example.starscoffee

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.quickfoods.models.Voucher
import com.evanemran.quickfoods.models.parseJsonToVouchersList
import com.example.starscoffee.adapters.CartListAdapter
import com.example.starscoffee.databinding.ActivityCartBinding
import com.example.starscoffee.dialogs.VoucherDialog
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
            val api = StarsCoffeeAPI()
            val voucherList = parseJsonToVouchersList(api.getAllVouchers())
            val voucherDialog = VoucherDialog(voucherList, voucherClickListener)
            voucherDialog.show(supportFragmentManager, "payment")
        }
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
        textViewTotal.text = getString(R.string.total_price, total)
    }

    // Function to calculate the subtotal
    fun calculateSubTotal() {
        val subTotal = cartList.sumOf { it.price * it.quantity }
        val textViewSubTotal = findViewById<TextView>(R.id.textView_subTotal)
        textViewSubTotal.text = getString(R.string.subtotal_price, subTotal)
    }

    private val cartClickListener: ClickListener<Foods> = object :
        ClickListener<Foods> {
        override fun onClicked(data: Foods) {
        }

    }

    private val voucherClickListener: ClickListener<Voucher> = object :
        ClickListener<Voucher> {
        override fun onClicked(data: Voucher) {
            println("buceta")
            Toast.makeText(this@CartActivity, data.voucherName + " voucher applied", Toast.LENGTH_SHORT).show()


            // Calculate the new subtotal after applying the coupon
            var total = cartList.sumOf { it.price * it.quantity } - data.value
            if(total < 0) {total=0}
            val textViewTotal = findViewById<TextView>(R.id.textView_total)
            textViewTotal.text = getString(R.string.total_price, total)
        }
    }
}