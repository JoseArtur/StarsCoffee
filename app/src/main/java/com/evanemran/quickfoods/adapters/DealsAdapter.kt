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
import com.evanemran.quickfoods.models.Service
import com.squareup.picasso.Picasso


class DealsAdapter (val context: Context, val list: List<Deals>, val listener: ClickListener<Deals>)
    : RecyclerView.Adapter<DealsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_deals, parent, false)
        return DealsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.imageView_deals.setImageResource(item.dealImage)


//        holder.service_container.setOnClickListener {
//            listener.onClicked(item)
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class DealsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView_deals: ImageView

    init {
        imageView_deals = itemView.findViewById(R.id.imageView_deals)
    }
}