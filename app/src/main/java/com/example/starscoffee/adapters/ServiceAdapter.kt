package com.example.starscoffee.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Service
import com.example.starscoffee.databinding.ListServicesBinding

class ServiceAdapter (val context: Context, val list: List<Service>, val listener: ClickListener<Service>)
    : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ListServicesBinding.inflate(layoutInflater, parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.binding.expandedLayout.visibility = View.GONE
        holder.binding.collapsedLayout.visibility = View.VISIBLE

        holder.binding.textViewServiceTitle.text = item.title
        holder.binding.textViewServiceSubtitle.text = item.subtitle
        holder.binding.textViewServiceSubtitleExpanded.text = item.subtitle
        holder.binding.imageViewService.setImageResource(item.icon)
        holder.binding.imageViewServiceExpanded.setImageResource(item.icon)

        holder.binding.serviceContainer.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ServiceViewHolder(val binding: ListServicesBinding) : RecyclerView.ViewHolder(binding.root)
}