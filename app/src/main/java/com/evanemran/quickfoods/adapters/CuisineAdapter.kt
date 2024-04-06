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
import com.evanemran.quickfoods.models.Service
import com.squareup.picasso.Picasso


class CuisineAdapter (val context: Context, val list: List<Cuisine>, val listener: ClickListener<Cuisine>)
    : RecyclerView.Adapter<CuisineViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisineViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_cuisine, parent, false)
        return CuisineViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CuisineViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.imageView_cuisine.setImageResource(item.cuisineImage)
        holder.textView_cuisine.setText(item.cuisineName)


        holder.cuisine_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CuisineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView_cuisine: ImageView
    var textView_cuisine: TextView
    var cuisine_container: CardView

    init {
        imageView_cuisine = itemView.findViewById(R.id.imageView_cuisine)
        textView_cuisine = itemView.findViewById(R.id.textView_cuisine)
        cuisine_container = itemView.findViewById(R.id.cuisine_container)
    }
}