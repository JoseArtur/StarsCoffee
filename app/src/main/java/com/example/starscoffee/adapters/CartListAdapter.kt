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
import com.example.starscoffee.models.Foods
import com.example.starscoffee.models.Service
import com.squareup.picasso.Picasso


import com.example.starscoffee.databinding.ListCartBinding

class CartListAdapter (val context: Context, val list: List<Foods>, val listener: ClickListener<Foods>)
    : RecyclerView.Adapter<CartListAdapter.CartListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListCartBinding.inflate(layoutInflater, parent, false)
        return CartListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        val item = list[holder.adapterPosition]
        val imageUrl = item.image_url
        Picasso.get().load(imageUrl).into(holder.binding.imageViewFoods)
        holder.binding.textViewFoodName.text = item.foodName
        holder.binding.textViewFoodPrice.text = "${item.price} €"

        holder.binding.cartListContainer.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CartListViewHolder(val binding: ListCartBinding) : RecyclerView.ViewHolder(binding.root)
}