package com.example.foodbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.data.Oder
import com.squareup.picasso.Picasso


class OderAdapter(val oder:List<Oder>, val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_notication,parent,false)
        return OderViewHolder(view)

    }

    override fun getItemCount(): Int {


        return oder.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentOder = oder[position]
        (holder as OderViewHolder).binData(currentOder)
    }

}

class OderViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    val tvOderName = view.findViewById<TextView>(R.id.txtOder12)
    val tvOderTime = view.findViewById<TextView>(R.id.txtconfirming)
    fun binData(oder:Oder){
        tvOderName.text = oder.OderName
        tvOderTime.text = oder.Time
    }
}
