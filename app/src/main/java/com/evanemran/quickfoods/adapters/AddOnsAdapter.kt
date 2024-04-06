package com.evanemran.quickfoods.adapters

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
import com.evanemran.quickfoods.R
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.*
import com.squareup.picasso.Picasso


class AddOnsAdapter (val context: Context, val list: List<AddOns>, val listener: ClickListener<AddOns>)
    : RecyclerView.Adapter<AddOnsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddOnsViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_addon, parent, false)
        return AddOnsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AddOnsViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.textView_addonName.setText(item.addOnName)


//        holder.drawer_container.setOnClickListener {
//            listener.onClicked(item)
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class AddOnsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView_addonName: TextView
    var textView_addonPrice: TextView

    init {
        textView_addonName = itemView.findViewById(R.id.textView_addonName)
        textView_addonPrice = itemView.findViewById(R.id.textView_addonPrice)
    }
}