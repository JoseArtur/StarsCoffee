package com.example.starscoffee.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.PaymentChannels
import com.example.starscoffee.databinding.ListPaychannelBinding


class PayChannelAdapter (val context: Context, val selectedChannel: PaymentChannels, val list: List<PaymentChannels>, val listener: ClickListener<PaymentChannels>)
    : RecyclerView.Adapter<PayChannelAdapter.PayChannelViewHolder>(){
    private var selectedPos = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayChannelViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ListPaychannelBinding.inflate(layoutInflater, parent, false)
        return PayChannelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PayChannelViewHolder, position: Int) {
        selectedPos = getSelectedPos()
        val item = list[holder.adapterPosition]

        holder.binding.imageViewPaySelected.visibility = (
                if (selectedPos == holder.adapterPosition) View.VISIBLE
                else View.GONE
                )

        holder.binding.textViewPayChannel.text = item.pName
        holder.binding.imageViewPayChannel.setImageResource(item.pIcon)


        holder.binding.payChannelContainer.setOnClickListener {
            listener.onClicked(item)
            notifyItemChanged(selectedPos)
            selectedPos = holder.adapterPosition
            notifyItemChanged(selectedPos)
        }
    }

    private fun getSelectedPos(): Int {
        list.forEachIndexed { index, element ->
            if (element.pName == selectedChannel.pName){
                selectedPos = index
            }
        }

        return selectedPos

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PayChannelViewHolder(val binding: ListPaychannelBinding) : RecyclerView.ViewHolder(binding.root)
}