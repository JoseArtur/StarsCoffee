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

import com.example.starscoffee.databinding.ListFoodBinding

class FoodListAdapter (val context: Context, val list: List<Foods>, val listener: ClickListener<Foods>)
    : RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListFoodBinding.inflate(layoutInflater, parent, false)
        return FoodListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        Picasso.get().load(item.image_url).into(holder.binding.imageViewFoods)
        holder.binding.textViewFoodName.text = item.foodName
        holder.binding.textViewFoodInfo.text = item.description

        holder.binding.foodListContainer.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class FoodListViewHolder(val binding: ListFoodBinding) : RecyclerView.ViewHolder(binding.root)
}