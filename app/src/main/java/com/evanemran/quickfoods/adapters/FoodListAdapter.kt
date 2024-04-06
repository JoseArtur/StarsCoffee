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
import com.evanemran.quickfoods.models.Deals
import com.evanemran.quickfoods.models.Foods
import com.evanemran.quickfoods.models.Service
import com.squareup.picasso.Picasso


class FoodListAdapter (val context: Context, val list: List<Foods>, val listener: ClickListener<Foods>)
    : RecyclerView.Adapter<FoodListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_food, parent, false)
        return FoodListViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        Picasso.get().load(item.image_url).into(holder.imageView_foods)
        holder.textView_foodName.setText(item.foodName)
        holder.textView_foodInfo.setText(item.description)


        holder.foodList_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class FoodListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView_foods: ImageView
    var textView_foodName: TextView
    var textView_foodInfo: TextView
    var textView_foodPrice: TextView
    var foodList_container: CardView

    init {
        imageView_foods = itemView.findViewById(R.id.imageView_foods)
        textView_foodName = itemView.findViewById(R.id.textView_foodName)
        textView_foodInfo = itemView.findViewById(R.id.textView_foodInfo)
        textView_foodPrice = itemView.findViewById(R.id.textView_foodPrice)
        foodList_container = itemView.findViewById(R.id.foodList_container)
    }
}