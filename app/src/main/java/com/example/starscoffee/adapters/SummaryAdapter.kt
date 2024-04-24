package com.example.starscoffee.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starscoffee.databinding.ListSummaryBinding
import com.example.starscoffee.models.Foods

class SummaryAdapter(val context: Context, val list: List<Foods>) :
    RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ListSummaryBinding.inflate(layoutInflater, parent, false)
        return SummaryViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.binding.textViewFoodName.text = item.foodName
        if (item.quantity > 1) {
            holder.binding.textViewFoodPrice.text = "${item.quantity} x ${item.price} €"
        } else {
            holder.binding.textViewFoodPrice.text = "${item.price} €"
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class SummaryViewHolder(val binding: ListSummaryBinding) :
        RecyclerView.ViewHolder(binding.root)
}