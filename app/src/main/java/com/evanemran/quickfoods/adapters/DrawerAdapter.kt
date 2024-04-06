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
import com.evanemran.quickfoods.models.Cuisine
import com.evanemran.quickfoods.models.Deals
import com.evanemran.quickfoods.models.DrawerMenu
import com.evanemran.quickfoods.models.Service
import com.squareup.picasso.Picasso


class DrawerAdapter (val context: Context, val list: List<DrawerMenu>, val listener: ClickListener<DrawerMenu>)
    : RecyclerView.Adapter<DrawerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_drawer, parent, false)
        return DrawerViewHolder(layout)
    }

    override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.imageView_drawer.setImageResource(item.icon)
        holder.textView_drawer.setText(item.title)


        holder.drawer_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class DrawerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView_drawer: ImageView
    var textView_drawer: TextView
    var drawer_container: LinearLayout

    init {
        imageView_drawer = itemView.findViewById(R.id.imageView_drawer)
        textView_drawer = itemView.findViewById(R.id.textView_drawer)
        drawer_container = itemView.findViewById(R.id.drawer_container)
    }
}