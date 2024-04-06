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
import com.evanemran.quickfoods.models.Cuisine
import com.evanemran.quickfoods.models.Deals
import com.evanemran.quickfoods.models.Restaurant
import com.evanemran.quickfoods.models.Service
import com.squareup.picasso.Picasso


class RestaurantAdapter (val context: Context, val list: List<Restaurant>, val listener: ClickListener<Restaurant>)
    : RecyclerView.Adapter<RestaurantViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_restaurants, parent, false)
        return RestaurantViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.textView_restaurantName.setText(item.restaurantName)


        holder.food_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView_restaurantName: TextView
    var food_container: CardView

    init {
        textView_restaurantName = itemView.findViewById(R.id.textView_restaurantName)
        food_container = itemView.findViewById(R.id.food_container)
    }
}