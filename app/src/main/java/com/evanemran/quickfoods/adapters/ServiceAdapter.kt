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
import com.evanemran.quickfoods.models.Service
import com.squareup.picasso.Picasso


class ServiceAdapter (val context: Context, val list: List<Service>, val listener: ClickListener<Service>)
    : RecyclerView.Adapter<ServiceViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_services, parent, false)
        return ServiceViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val item = list[holder.adapterPosition]



        holder.expanded_layout.visibility = View.GONE
        holder.collapsed_layout.visibility = View.VISIBLE

        holder.textView_service_title.text = item.title
        holder.textView_service_subtitle.text = item.subtitle
        holder.textView_service_subtitle_expanded.text = item.subtitle
        holder.imageView_service.setImageResource(item.icon)
        holder.imageView_service_expanded.setImageResource(item.icon)


        holder.service_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView_service: ImageView
    var imageView_service_expanded: ImageView
    var textView_service_title: TextView
    var textView_service_subtitle: TextView
    var textView_service_subtitle_expanded: TextView
    var service_container: CardView
    var expanded_layout: LinearLayout
    var collapsed_layout: LinearLayout

    init {
        imageView_service = itemView.findViewById(R.id.imageView_service)
        imageView_service_expanded = itemView.findViewById(R.id.imageView_service_expanded)
        textView_service_title = itemView.findViewById(R.id.textView_service_title)
        textView_service_subtitle = itemView.findViewById(R.id.textView_service_subtitle)
        textView_service_subtitle_expanded = itemView.findViewById(R.id.textView_service_subtitle_expanded)
        service_container = itemView.findViewById(R.id.service_container)
        expanded_layout = itemView.findViewById(R.id.expanded_layout)
        collapsed_layout = itemView.findViewById(R.id.collapsed_layout)
    }
}