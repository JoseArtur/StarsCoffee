package com.example.starscoffee

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
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
import com.google.gson.Gson
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.nio.ByteBuffer


data class OrderInfo(
    val orderItems: List<Foods>,
    val paymentMethod: PaymentChannels,
    val vouchersUsed: List<Voucher>,
    val totalCost: String,
    val userEmail: String
)


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
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "jose@feup.pt")
        println("Email: $email")
        binding.buttonCheckOut.setOnClickListener {
            val orderInfo = email?.let { it1 ->
                OrderInfo(
                    orderItems = cartList,
                    paymentMethod = selectedChannel!!,
                    vouchersUsed = voucherList,
                    totalCost = binding.textViewTotal.text.toString(),
                    userEmail = it1
                )
            }
            if (orderInfo != null) {
                for (item in orderInfo.orderItems) {
                    item.imageUrl = ""
                    item.description = ""
                    item.customizationOptions = ""
                }
            }
            if (orderInfo != null) {
                for (item in orderInfo.vouchersUsed) {
                    item.image_url = ""
                    item.description = ""
                }
            }
            val orderInfoJson = Gson().toJson(orderInfo)

            // do a for loop in the orderInfoJson to change all orderItems: imageUrl to ""


println("Order Info: $orderInfoJson")
            val byteArray = orderInfoJson.toByteArray(Charsets.UTF_8)
           println("Order Info: $byteArray")
            val jsonString = String(byteArray, Charsets.UTF_8)
            println("Order Info: $jsonString")

            val intent = Intent(this, OrderStatusActivity::class.java)
            intent.putExtra("orderInfo", byteArray)
            startActivity(intent)
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
        println(intent.getStringExtra("voucherTotal"))
        if (intent.getStringExtra("voucherTotal")== "0.0") {
            val linearLayout = findViewById<LinearLayout>(R.id.linear_layout)
            linearLayout.visibility = View.GONE

        } else {
            binding.textViewVoucherTotal.text = "- " + intent.getStringExtra("voucherTotal") + " €"
        }
    }


}