package com.example.foodbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.data.NewEvents
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class NeventAdapter(val nevent :List<NewEvents>, val context: Context?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_newandevent,parent,false)
        return NeweventViewHolder(view)

    }

    override fun getItemCount(): Int {
        return nevent.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currenEvents = nevent[position]
        (holder as NeweventViewHolder ).binData(currenEvents)

    }


}


class NeweventViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    val tvEventName = view.findViewById<TextView>(R.id.txtNewevent)
    val tvEventTime = view.findViewById<TextView>(R.id.txtNewevent2)
    val profileImage = view.findViewById<CircleImageView>(R.id.imgNewevent);


    fun binData(nevent:NewEvents){
        tvEventName.text = nevent.nEventName
        tvEventTime.text = nevent.nEventTime
        Picasso.get()
            .load("https://images.foody.vn/res/g93/920647/prof/s640x400/foody-upload-api-foody-mobile-lklk-190522154211.jpg")
            .into(profileImage);

    }
}