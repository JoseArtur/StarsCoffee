package com.example.starscoffee.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.quickfoods.models.Voucher
import com.example.starscoffee.databinding.ListSummaryBinding
import com.example.starscoffee.models.Foods

class VoucherCheckoutAdapter(val context: Context, val list: List<Voucher>) :
    RecyclerView.Adapter<VoucherCheckoutAdapter.VoucherCheckoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherCheckoutViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ListSummaryBinding.inflate(layoutInflater, parent, false)
        return VoucherCheckoutViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VoucherCheckoutViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.binding.textViewFoodName.text = item.voucherName
        holder.binding.textViewFoodPrice.text = "- ${item.value} €"
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class VoucherCheckoutViewHolder(val binding: ListSummaryBinding) :
        RecyclerView.ViewHolder(binding.root)
}