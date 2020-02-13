package com.example.foodbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.data.PreOrders


class PreOdersAdapter(val pre:List<PreOrders>, val context : Context?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pre_orders,parent,false)
        return PreOdersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pre.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentPre = pre[position]
        (holder as PreOdersViewHolder).bindData(currentPre)
    }
}

class PreOdersViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    val tvID = view.findViewById<TextView>(R.id.tvPreID)
    val tvTime = view.findViewById<TextView>(R.id.tvPreTime)
    val tvDetail = view.findViewById<TextView>(R.id.tvPreDetail)
    val tvCost = view.findViewById<TextView>(R.id.tvPreCost)

    fun bindData(pre : PreOrders){
        tvID.text = pre.PreId
        tvTime.text = pre.PreTime
        tvDetail.text = pre.PreDetail
        tvCost.text = pre.PreCost
    }

}