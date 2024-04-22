package com.example.starscoffee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evanemran.quickfoods.models.Voucher
import com.example.starscoffee.databinding.ActivityVoucherDetailBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class VoucherDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoucherDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val voucherString = intent.getStringExtra("voucher")
        val gson = Gson()
        val voucher: Voucher = gson.fromJson(voucherString, Voucher::class.java)
        binding = ActivityVoucherDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupVoucher(voucher)
    }

    private fun setupVoucher(voucher: Voucher) {

        // Update the text property of the TextView
        binding.textViewVoucherNameDetail.text = voucher.voucherName
        binding.textViewVoucherInfo.text = voucher.description
        binding.textViewVoucherPrice.text = voucher.pointsRequired.toString() + " points"



        // Load the image from the URL into the ImageView using Picasso
        Picasso.get().load(voucher.image_url).into(binding.voucherImage)
    }

}