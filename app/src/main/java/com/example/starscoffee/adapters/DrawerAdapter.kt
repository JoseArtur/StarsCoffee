package com.example.starscoffee.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starscoffee.databinding.ListDrawerBinding
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.DrawerMenu

class DrawerAdapter(
    val context: Context,
    val list: List<DrawerMenu>,
    val listener: ClickListener<DrawerMenu>
) : RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListDrawerBinding.inflate(layoutInflater, parent, false)
        return DrawerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.binding.imageViewDrawer.setImageResource(item.icon)
        holder.binding.textViewDrawer.text = item.title

        holder.binding.drawerContainer.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class DrawerViewHolder(val binding: ListDrawerBinding) :
        RecyclerView.ViewHolder(binding.root)
}