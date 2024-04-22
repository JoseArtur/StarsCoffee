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
import com.example.starscoffee.models.Foods

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    var tempUserPoints = 0
    var permUserPoints = 0
    var voucherTotal = 0
    var voucherList: MutableList<Voucher> = mutableListOf()

    companion object {
        val cartList: MutableList<Foods> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tempUserPoints = intent.getIntExtra("userPoints", 0)
        permUserPoints = tempUserPoints
        println("User points: " + tempUserPoints)
        setupPointsMenu(tempUserPoints)

        setupCartData()

        binding.buttonReviewAddress.setOnClickListener {
            if(cartList.size == 0){
                Toast.makeText(
                    this@CartActivity,
                    "Cart cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                startActivity(
                    Intent(this, CheckoutActivity::class.java)
                        .putExtra("cartList", ArrayList(cartList))
                        .putExtra("total", binding.textViewTotal.text.toString())
                        .putExtra("subTotal", binding.textViewSubTotal.text.toString())
                        .putExtra("userPoints", tempUserPoints.toString())
                        .putExtra("voucherTotal", voucherTotal.toString())
                        .putExtra("vouchersApplied", ArrayList(voucherList))
                )
            }
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

    private fun setupPointsMenu(points: Int) {
        binding.pointsButton.run { binding.pointsButton.setText(points.toString())}
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
            if(tempUserPoints >= data.pointsRequired) {
                Toast.makeText(
                    this@CartActivity,
                    data.voucherName + " voucher applied",
                    Toast.LENGTH_SHORT
                ).show()

                // Calculate the new subtotal after applying the coupon
                voucherTotal += data.value
                var total = cartList.sumOf { it.price * it.quantity }
                if (voucherTotal > total) {
                    voucherTotal = total
                    total = 0
                }
                else {
                    total -= voucherTotal
                }
                tempUserPoints -= data.pointsRequired
                setupPointsMenu(tempUserPoints)
                val textViewTotal = findViewById<TextView>(R.id.textView_total)
                textViewTotal.text = getString(R.string.total_price, total)
                voucherList.add(data)
            }
            else{
                Toast.makeText(
                    this@CartActivity,
                    "You do not have enough points to apply this voucher",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}