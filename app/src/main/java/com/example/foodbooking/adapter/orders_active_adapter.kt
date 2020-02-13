package com.example.foodbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.data.Oders_Active

class OdersActiveAdapter(val active :List<Oders_Active>, val context: Context?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_active_orders,parent,false)
        return OdersAciveViewHolder(view)
    }

    override fun getItemCount(): Int {
        return active.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentActive = active[position]
        (holder as OdersAciveViewHolder ).bindData(currentActive)
    }
}

class OdersAciveViewHolder(val view: View): RecyclerView.ViewHolder(view){
    val nameActive = view.findViewById<TextView>(R.id.tvActiveName)
    val coutActive = view.findViewById<TextView>(R.id.tvActiveCount)
    val costActive = view.findViewById<TextView>(R.id.tvActiveMoney)

    fun bindData(ac : Oders_Active){
        nameActive.text = ac.NameOrderActive
        coutActive.text = ac.CountOrderACtive.toString()
        costActive.text = ac.PriceOrderActive.toString()
    }

}