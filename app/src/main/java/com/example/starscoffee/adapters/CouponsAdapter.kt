package com.example.starscoffee.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starscoffee.databinding.ListCouponBinding
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Coupon

class CouponsAdapter(
    val context: Context,
    val list: List<Coupon>,
    val listener: ClickListener<Coupon>
) : RecyclerView.Adapter<CouponsAdapter.CouponsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListCouponBinding.inflate(layoutInflater, parent, false)
        return CouponsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CouponsViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.binding.textViewCTitle.text = (item.couponTitle)
        holder.binding.textViewCCode.text = item.couponCode
        holder.binding.textViewCAmount.text = item.couponAmt.toString()
        holder.binding.textViewCValidity.text = item.couponValidity

        holder.binding.couponContainer.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CouponsViewHolder(val binding: ListCouponBinding) :
        RecyclerView.ViewHolder(binding.root)
}