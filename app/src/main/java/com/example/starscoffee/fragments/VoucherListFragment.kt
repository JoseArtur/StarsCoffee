package com.example.starscoffee.fragments

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
import com.example.starscoffee.StarsCoffeeAPI
import com.example.starscoffee.databinding.FragmentVoucherBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class VoucherListFragment : Fragment() {
        private var _binding: FragmentVoucherBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
                _binding = FragmentVoucherBinding.inflate(inflater, container, false)


                lifecycleScope.launch {
                        val voucherList = getVouchersList()

                        binding.recyclerVouchers.apply {
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                                adapter = VoucherListAdapter(context, voucherList, voucherClickListener)
                        }
                }

                return binding.root
        }

private fun getVouchersList(): List<Voucher> {
        val api = StarsCoffeeAPI()
        val vouchersDeferred = api.getAllVouchers()
        var v2 = ""

        // Use a CoroutineScope to wait for the result of the coroutine
        runBlocking {
                // Wait for the result and access the list of vouchers
                val vouchers = vouchersDeferred.await()
                v2 = vouchers.toString()
                // Process the list of vouchers as needed
                println("Vouchers2: $vouchers")
        }
        println("Vouchers3: $v2")
        return emptyList()
}

private val voucherClickListener = object : ClickListener<Voucher> {
        override fun onClicked(data: Voucher) {
                val gson = Gson()
                val jsonString = gson.toJson(data)
                startActivity(Intent(context, VoucherDetailActivity::class.java).putExtra("voucher", jsonString))
                }
        }
}