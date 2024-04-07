package com.example.starscoffee.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.*
import com.example.starscoffee.databinding.ListAddonBinding
import com.squareup.picasso.Picasso


class AddOnsAdapter (val context: Context, val list: List<AddOns>, val listener: ClickListener<AddOns>)
    : RecyclerView.Adapter<AddOnsAdapter.AddOnsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddOnsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListAddonBinding.inflate(layoutInflater, parent, false)
        return AddOnsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddOnsViewHolder, position: Int) {
        val item = list[holder.adapterPosition]
        holder.binding.textViewAddonName.text = item.addOnName
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class AddOnsViewHolder(val binding: ListAddonBinding) : RecyclerView.ViewHolder(binding.root)
}