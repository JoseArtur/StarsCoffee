package com.evanemran.quickfoods.adapters

import android.annotation.SuppressLint
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
import com.evanemran.quickfoods.models.Cuisine
import com.evanemran.quickfoods.models.Deals
import com.evanemran.quickfoods.models.Foods
import com.evanemran.quickfoods.models.Service
import com.squareup.picasso.Picasso


class SummaryAdapter (val context: Context, val list: List<Foods>)
    : RecyclerView.Adapter<SummaryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_summary, parent, false)
        return SummaryViewHolder(layout)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.textView_foodName.setText(item.foodName)
        holder.textView_foodPrice.setText( item.price.toString() + "€")

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class SummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView_foodName: TextView
    var textView_foodPrice: TextView
    var cartList_container: CardView

    init {
        textView_foodName = itemView.findViewById(R.id.textView_foodName)
        textView_foodPrice = itemView.findViewById(R.id.textView_foodPrice)
        cartList_container = itemView.findViewById(R.id.cartList_container)
    }
}