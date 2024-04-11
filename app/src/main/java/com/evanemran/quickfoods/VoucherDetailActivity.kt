package com.evanemran.quickfoods

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.evanemran.quickfoods.models.Vouchers
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_voucher_detail.textView_VoucherInfo
import kotlinx.android.synthetic.main.activity_voucher_detail.textView_voucherNameDetail
import kotlinx.android.synthetic.main.activity_voucher_detail.voucherImage

class VoucherDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val voucherString = intent.getStringExtra("voucher")
        System.out.println("Guaras é do Maranhao:" + voucherString)
        val gson = Gson()
        val voucher: Vouchers = gson.fromJson(voucherString, Vouchers::class.java)
        setContentView(R.layout.activity_voucher_detail)

        setupVoucher(voucher)
    }

    private fun setupVoucher(voucher: Vouchers) {
        val textViewVoucherName = findViewById<TextView>(R.id.textView_voucherNameDetail)

        // Update the text property of the TextView
        textViewVoucherName.text = voucher.voucherName
        textView_VoucherInfo.text = voucher.description
        val imageViewVoucher = findViewById<ImageView>(R.id.voucherImage)

        // Load the image from the URL into the ImageView using Picasso
        Picasso.get().load(voucher.image_url).into(imageViewVoucher)
    }

}