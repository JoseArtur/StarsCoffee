package com.evanemran.quickfoods.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.quickfoods.R
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.Foods
import com.evanemran.quickfoods.models.Vouchers
import com.squareup.picasso.Picasso


class VoucherListAdapter (val context: Context, val list: List<Vouchers>, val listener: ClickListener<Vouchers>)
    : RecyclerView.Adapter<VoucherListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherListViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_voucher, parent, false)
        return VoucherListViewHolder(layout)
    }

    override fun onBindViewHolder(holder: VoucherListViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        Picasso.get().load(item.image_url).into(holder.imageView_vouchers)
        holder.textView_voucherName.setText(item.voucherName)
        holder.textView_voucherInfo.setText(item.description)


        holder.voucherList_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class VoucherListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView_vouchers: ImageView
    var textView_voucherName: TextView
    var textView_voucherInfo: TextView
    var textView_voucherPrice: TextView
    var voucherList_container: CardView

    init {
        imageView_vouchers = itemView.findViewById(R.id.imageView_vouchers)
        textView_voucherName = itemView.findViewById(R.id.textView_voucherName)
        textView_voucherInfo = itemView.findViewById(R.id.textView_voucherInfo)
        textView_voucherPrice = itemView.findViewById(R.id.textView_voucherPrice)
        voucherList_container = itemView.findViewById(R.id.voucherList_container)
    }
}