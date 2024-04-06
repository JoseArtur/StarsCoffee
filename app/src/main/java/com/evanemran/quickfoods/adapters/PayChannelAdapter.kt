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
import com.evanemran.quickfoods.models.PaymentChannels


class PayChannelAdapter (val context: Context, val selectedChannel: PaymentChannels, val list: List<PaymentChannels>, val listener: ClickListener<PaymentChannels>)
    : RecyclerView.Adapter<PayChannelViewHolder>(){
    private var selectedPos = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayChannelViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_paychannel, parent, false)
        return PayChannelViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PayChannelViewHolder, position: Int) {
        selectedPos = getSelectedPos()
        val item = list[holder.adapterPosition]

        holder.imageView_paySelected.visibility = (
            if (selectedPos == holder.adapterPosition) View.VISIBLE
            else View.GONE
        )

        holder.textView_payChannel.setText(item.pName)
        holder.imageView_payChannel.setImageResource(item.pIcon)


        holder.payChannel_container.setOnClickListener {
//            holder.imageView_paySelected.visibility = View.VISIBLE
            listener.onClicked(item)
            //                holder.textView_cat.setBackgroundColor(Color.parseColor("#4694E3"));
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
}

class PayChannelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView_payChannel: ImageView
    var textView_payChannel: TextView
    var imageView_paySelected: ImageView
    var payChannel_container: LinearLayout

    init {
        imageView_payChannel = itemView.findViewById(R.id.imageView_payChannel)
        textView_payChannel = itemView.findViewById(R.id.textView_payChannel)
        imageView_paySelected = itemView.findViewById(R.id.imageView_paySelected)
        payChannel_container = itemView.findViewById(R.id.payChannel_container)
    }
}