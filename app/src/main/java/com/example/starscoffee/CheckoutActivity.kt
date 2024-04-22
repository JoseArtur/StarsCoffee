package com.example.starscoffee

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.quickfoods.models.Voucher
import com.example.starscoffee.adapters.SummaryAdapter
import com.example.starscoffee.adapters.VoucherCheckoutAdapter
import com.example.starscoffee.databinding.ActivityCheckoutBinding
import com.example.starscoffee.dialogs.PaymentOptionDialog
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Foods
import com.example.starscoffee.models.PaymentChannels

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private var selectedChannel: PaymentChannels? = null
    private lateinit var cartList: ArrayList<Foods>
    private lateinit var voucherList: ArrayList<Voucher>
    private var userPoints = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userPoints = intent.getStringExtra("userPoints").toString()
        println("userPoints: $userPoints")

        setupCheckoutData()
        setupVoucherList()
        selectedChannel = PaymentChannels(0, "Cash", R.drawable.ic_money)
        //  cartList = intent.getSerializableExtra("cartList") as ArrayList<Foods>

        binding.textViewPayChannel.setOnClickListener {
            val postDialog = PaymentOptionDialog(
                getPayChannelList(),
                selectedChannel!!, paymentClickListener
            )
            postDialog.show(supportFragmentManager, "payment")
        }

        binding.buttonCheckOut.setOnClickListener {
            startActivity(Intent(this, OrderStatusActivity::class.java))
            
        }

    }

    private fun getPayChannelList(): MutableList<PaymentChannels> {
        val payChannels: MutableList<PaymentChannels> = mutableListOf()
        payChannels.add(PaymentChannels(0, "Cash", R.drawable.ic_money))
        payChannels.add(PaymentChannels(0, "Card", R.drawable.ic_visa_logo))
        return payChannels
    }

    private val paymentClickListener: ClickListener<PaymentChannels> =
        object : ClickListener<PaymentChannels> {
            override fun onClicked(data: PaymentChannels) {
                selectedChannel = data
                binding.imageViewSelectedPayChannel.setImageResource(data.pIcon)
                binding.textViewPaymentType.text = data.pName
                Toast.makeText(this@CheckoutActivity, "Selected " + data.pName, Toast.LENGTH_SHORT)
                    .show()
            }

        }

    private fun setupCheckoutData() {
        cartList = intent.getSerializableExtra("cartList") as ArrayList<Foods>
        binding.recyclerSummary.setHasFixedSize(true)
        binding.recyclerSummary.isNestedScrollingEnabled = false
        binding.recyclerSummary.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val summaryAdapter = SummaryAdapter(this, cartList)
        binding.recyclerSummary.adapter = summaryAdapter
        // Calculate the subtotal and total initially
        binding.textViewSubtotal.text = intent.getStringExtra("subTotal")
        binding.textViewTotal.text = intent.getStringExtra("total")
    }

    private fun setupVoucherList() {
        voucherList = intent.getSerializableExtra("vouchersApplied") as ArrayList<Voucher>
        binding.recyclerVouchers.setHasFixedSize(true)
        binding.recyclerVouchers.isNestedScrollingEnabled = false
        binding.recyclerVouchers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val voucherAdapter = VoucherCheckoutAdapter(this, voucherList)
        binding.recyclerVouchers.adapter = voucherAdapter
        // Calculate the subtotal and total initially
        binding.textViewVoucherTotal.text = "- " + intent.getStringExtra("voucherTotal") + " €"
    }


}