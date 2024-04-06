package com.evanemran.quickfoods.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.quickfoods.R
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.Deals
import com.evanemran.quickfoods.models.Foods
import com.evanemran.quickfoods.models.Service
import com.squareup.picasso.Picasso


class CartListAdapter (val context: Context, val list: List<Foods>, val listener: ClickListener<Foods>)
    : RecyclerView.Adapter<CartListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_cart, parent, false)
        return CartListViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        val item = list[holder.adapterPosition]
        val imageUrl = item.image_url
        val imageView_foods = holder.imageView_foods
        Picasso.get().load(imageUrl).into(imageView_foods)
        holder.textView_foodName.setText(item.foodName)
        holder.textView_foodPrice.setText(item.price.toString() + " €")


        holder.cartList_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CartListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView_foods: ImageView
    var textView_foodName: TextView
    var textView_foodPrice: TextView
    var cartList_container: CardView

    init {
        imageView_foods = itemView.findViewById(R.id.imageView_foods)
        textView_foodName = itemView.findViewById(R.id.textView_foodName)
        textView_foodPrice = itemView.findViewById(R.id.textView_foodPrice)
        cartList_container = itemView.findViewById(R.id.cartList_container)
    }
}