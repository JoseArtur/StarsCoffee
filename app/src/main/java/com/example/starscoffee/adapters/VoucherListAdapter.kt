package com.example.starscoffee.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.quickfoods.models.Voucher
import com.example.starscoffee.databinding.ListVoucherBinding
import com.example.starscoffee.listeners.ClickListener
import com.squareup.picasso.Picasso


class VoucherListAdapter (val context: Context, val list: List<Voucher>, val listener: ClickListener<Voucher>)
    : RecyclerView.Adapter<VoucherListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListVoucherBinding.inflate(layoutInflater, parent, false)
        return VoucherListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VoucherListViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        Picasso.get().load(item.image_url).into(holder.imageView_vouchers)
        holder.textView_voucherName.setText(item.voucherName)
        holder.textView_voucherInfo.setText(item.description)
        holder.textView_voucherPrice.setText(item.pointsRequired.toString() + " points")


        holder.voucherList_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class VoucherListViewHolder(val binding: ListVoucherBinding) : RecyclerView.ViewHolder(binding.root){
    var imageView_vouchers: ImageView
    var textView_voucherName: TextView
    var textView_voucherInfo: TextView
    var textView_voucherPrice: TextView
    var voucherList_container: CardView

    init {
        imageView_vouchers = binding.imageViewVouchers
        textView_voucherName = binding.textViewVoucherName
        textView_voucherInfo = binding.textViewVoucherInfo
        textView_voucherPrice = binding.textViewVoucherPrice
        voucherList_container = binding.voucherListContainer
    }
}