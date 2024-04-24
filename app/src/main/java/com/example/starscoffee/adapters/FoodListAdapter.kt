package com.example.starscoffee.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starscoffee.databinding.ListFoodBinding
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Foods
import com.squareup.picasso.Picasso

class FoodListAdapter(
    val context: Context,
    val list: List<Foods>,
    val listener: ClickListener<Foods>
) : RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListFoodBinding.inflate(layoutInflater, parent, false)
        return FoodListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        Picasso.get().load(item.imageUrl).into(holder.binding.imageViewFoods)
        holder.binding.textViewFoodName.text = item.foodName
        holder.binding.textViewFoodInfo.text = item.description
        holder.binding.textViewFoodPrice.text = item.price.toString()

        holder.binding.foodListContainer.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class FoodListViewHolder(val binding: ListFoodBinding) :
        RecyclerView.ViewHolder(binding.root)
}