package com.example.starscoffee.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Deals
import com.example.starscoffee.models.Service
import com.squareup.picasso.Picasso


import com.example.starscoffee.databinding.ListDealsBinding

class DealsAdapter (val context: Context, val list: List<Deals>, val listener: ClickListener<Deals>)
    : RecyclerView.Adapter<DealsAdapter.DealsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListDealsBinding.inflate(layoutInflater, parent, false)
        return DealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {
        val item = list[holder.adapterPosition]
        holder.binding.imageViewDeals.setImageResource(item.dealImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class DealsViewHolder(val binding: ListDealsBinding) : RecyclerView.ViewHolder(binding.root)
}