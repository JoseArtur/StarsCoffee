package com.example.starscoffee.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.starscoffee.CartActivity
import com.example.starscoffee.R
import com.example.starscoffee.databinding.ListCartBinding
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Foods
import com.squareup.picasso.Picasso

class CartListAdapter(
    val context: Context,
    val list: MutableList<Foods>,
    val listener: ClickListener<Foods>
) : RecyclerView.Adapter<CartListAdapter.CartListViewHolder>() {

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
        holder.binding.textViewFoodQuantity.text = "Qty: ${item.quantity}"
        holder.binding.textViewFoodPrice.text = "${item.price * item.quantity} €"

        holder.binding.cartListContainer.setOnClickListener {
            listener.onClicked(item)
        }
        // Set an OnClickListener for the remove_item button
        holder.binding.removeItem.setOnClickListener {
            list.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, list.size)

            // Calculate the subtotal after an item is removed
            if (context is CartActivity) {
                context.calculateSubTotal()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CartListViewHolder(val binding: ListCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val removeItem: ImageButton = binding.root.findViewById(R.id.remove_item)
    }
}