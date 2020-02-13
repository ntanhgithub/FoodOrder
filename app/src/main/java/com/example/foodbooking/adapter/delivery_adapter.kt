package com.example.foodbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.data.Delivery
import com.squareup.picasso.Picasso

class DeliveryAdapter(val deli:List<Delivery>,val context:Context?):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_delivery,parent,false)
        return DeliveryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deli.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curern = deli[position]
        (holder as DeliveryViewHolder).bindData(curern)
    }
}
class DeliveryViewHolder(val view:View):RecyclerView.ViewHolder(view){
    val named = view.findViewById<TextView>(R.id.tvDeliveryName)
    val price = view.findViewById<TextView>(R.id.tvDeliveryPrice)
    val img = view.findViewById<ImageView>(R.id.imgDelivery)
    val detail = view.findViewById<TextView>(R.id.tvDeliveryDetail)
    val btAdd = view.findViewById<Button>(R.id.btAddDelivery)
    fun bindData(deli:Delivery){
        named.text = deli.DeliveryName
        price.text = deli.DeliveryPrice.text
        detail.text = deli.DeliveryDescription
        Picasso.get().load(deli.DeliveryImage).into(img)
        btAdd.setOnClickListener {
            Toast.makeText(view.context,deli.DeliveryId.toString(),Toast.LENGTH_SHORT).show()
        }
    }

}