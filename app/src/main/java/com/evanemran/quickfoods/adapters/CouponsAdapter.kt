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
import com.evanemran.quickfoods.models.Coupon
import com.evanemran.quickfoods.models.PaymentChannels


class CouponsAdapter (val context: Context, val list: List<Coupon>, val listener: ClickListener<Coupon>)
    : RecyclerView.Adapter<CouponsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponsViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_coupon, parent, false)
        return CouponsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CouponsViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.textView_cTitle.text = (item.couponTitle)
        holder.textView_cCode.text = item.couponCode
        holder.textView_cAmount.text = item.couponAmt.toString()
        holder.textView_cValidity.text = item.couponValidity


        holder.coupon_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CouponsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var coupon_container: CardView
    var textView_cTitle: TextView
    var textView_cCode: TextView
    var textView_cAmount: TextView
    var textView_cValidity: TextView

    init {
        textView_cTitle = itemView.findViewById(R.id.textView_cTitle)
        textView_cCode = itemView.findViewById(R.id.textView_cCode)
        textView_cAmount = itemView.findViewById(R.id.textView_cAmount)
        textView_cValidity = itemView.findViewById(R.id.textView_cValidity)
        coupon_container = itemView.findViewById(R.id.coupon_container)
    }
}