package com.evanemran.quickfoods.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.quickfoods.FoodDetailActivity
import com.evanemran.quickfoods.R
import com.evanemran.quickfoods.VoucherDetailActivity
import com.evanemran.quickfoods.adapters.FoodListAdapter
import com.evanemran.quickfoods.adapters.VoucherListAdapter
import com.evanemran.quickfoods.dialogs.FoodDetailBottomSheet
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.Foods
import com.evanemran.quickfoods.models.Vouchers
import com.evanemran.quickfoods.models.menuItems
import com.evanemran.quickfoods.models.voucherItems
import com.google.gson.Gson

class VoucherListFragment : Fragment() {
private var view: View? = null

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_voucher, container, false)

        val recyclerVouchers = view?.findViewById<RecyclerView>(R.id.recycler_vouchers)

        recyclerVouchers?.apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = VoucherListAdapter(context, getVouchersList(), voucherClickListener)
        }

        return view
        }

private fun getVouchersList(): List<Vouchers> = voucherItems

private val voucherClickListener = object : ClickListener<Vouchers> {
        override fun onClicked(data: Vouchers) {
                val gson = Gson()
                val jsonString = gson.toJson(data)
                startActivity(Intent(context, VoucherDetailActivity::class.java).putExtra("voucher", jsonString))
                }
        }
}