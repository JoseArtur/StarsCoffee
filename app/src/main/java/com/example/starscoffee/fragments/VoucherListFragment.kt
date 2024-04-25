package com.example.starscoffee.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starscoffee.VoucherDetailActivity
import com.example.starscoffee.adapters.VoucherListAdapter
import com.example.starscoffee.listeners.ClickListener
import com.evanemran.quickfoods.models.Voucher
import com.evanemran.quickfoods.models.parseJsonToVouchersList
import com.example.starscoffee.StarsCoffeeAPI
import com.example.starscoffee.databinding.FragmentVoucherBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class VoucherListFragment(private val context: Context) : Fragment() {
        private var _binding: FragmentVoucherBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
                _binding = FragmentVoucherBinding.inflate(inflater, container, false)


                lifecycleScope.launch {
                        val voucherList = getVouchersList()

                        binding.recyclerVouchers.apply {
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                                adapter = voucherList?.let {
                                        VoucherListAdapter(context,
                                                it, voucherClickListener)
                                }
                        }
                }

                return binding.root
        }

fun getVouchersList(): List<Voucher>? {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")
        val api = StarsCoffeeAPI()
        val v2 = email?.let { api.getAllVouchers(it) }
        println("Vouchers3: $v2")
        return v2?.let { parseJsonToVouchersList(it) }
}

private val voucherClickListener = object : ClickListener<Voucher> {
        override fun onClicked(data: Voucher) {
                val gson = Gson()
                val jsonString = gson.toJson(data)
                startActivity(Intent(context, VoucherDetailActivity::class.java).putExtra("voucher", jsonString))
                }
        }
}