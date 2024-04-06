package com.evanemran.quickfoods.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.quickfoods.R
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.Foods
import com.squareup.picasso.Picasso


class RecentsAdapter (val context: Context, val list: List<Foods>, val listener: ClickListener<Foods>)
    : RecyclerView.Adapter<RecentsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentsViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_recents, parent, false)
        return RecentsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecentsViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.textView_foodName.setText(item.foodName)
        Picasso.get().load(item.image_url).into(holder.imageView_foods)
        holder.food_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class RecentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView_foodName: TextView
    var imageView_foods: ImageView
    var food_container: LinearLayout

    init {
        textView_foodName = itemView.findViewById(R.id.textView_foodName)
        food_container = itemView.findViewById(R.id.food_container)
        imageView_foods = itemView.findViewById(R.id.imageView_foods)
    }
}