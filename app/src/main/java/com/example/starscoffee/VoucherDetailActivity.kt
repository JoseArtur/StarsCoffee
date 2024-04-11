package com.example.starscoffee

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.evanemran.quickfoods.models.Vouchers
import com.example.starscoffee.databinding.ActivityRestaurantDetailBinding
import com.example.starscoffee.databinding.ActivityVoucherDetailBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class VoucherDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoucherDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val voucherString = intent.getStringExtra("voucher")
        val gson = Gson()
        val voucher: Vouchers = gson.fromJson(voucherString, Vouchers::class.java)
        binding = ActivityVoucherDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupVoucher(voucher)
    }

    private fun setupVoucher(voucher: Vouchers) {

        // Update the text property of the TextView
        binding.textViewVoucherNameDetail.text = voucher.voucherName
        binding.textViewVoucherInfo.text = voucher.description

        // Load the image from the URL into the ImageView using Picasso
        Picasso.get().load(voucher.image_url).into(binding.voucherImage)
    }

}