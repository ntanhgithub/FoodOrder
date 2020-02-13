package com.example.foodbooking.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.HomeActivity
import com.example.foodbooking.R
import com.example.foodbooking.data.HighLight_food
import com.example.foodbooking.data.ItemOders
import com.example.foodbooking.data.Oders_Current_Cart
import com.example.foodbooking.fragment.CurrentCartFragment
import com.squareup.picasso.Picasso

class HighlightAdapter(val highlight: List<HighLight_food>, val context: Context?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val oderItem = ArrayList<Oders_Current_Cart>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.highlight_item, parent, false)

        return HighlightViewHolder(view,oderItem)
    }

    override fun getItemCount(): Int {
        return highlight.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentHighlight_food = highlight[position]
        (holder as HighlightViewHolder).bindData(currentHighlight_food)
    }
}

class HighlightViewHolder(val view: View, val odersItem:ArrayList<Oders_Current_Cart>) : RecyclerView.ViewHolder(view) {
    val btnAdd = view.findViewById<Button>(R.id.btAdddHighLight)
    val imgFood = view.findViewById<ImageView>(R.id.imgHighlight)
    val tvNameFood = view.findViewById<TextView>(R.id.tvNameHighLightFood)
    val tvPriceFood = view.findViewById<TextView>(R.id.tvPriceHighLightFood)
    val tvNameRes = view.findViewById<TextView>(R.id.tvNameHighLightRestaurant)
    val tvDetailRes = view.findViewById<TextView>(R.id.tvDetailHighLightRestaurant)
    val logoRes = view.findViewById<ImageView>(R.id.imgLogoHighLight)
    val cusi = view.findViewById<TextView>(R.id.tvCuisicuiHighLightRestaurant)




    fun bindData(highlights: HighLight_food) {
        tvNameFood.text = highlights.NameHighlightFood
        tvPriceFood.text = highlights.PriceFood.text
        tvNameRes.text = highlights.NameHighlightRestaurant
        tvDetailRes.text = highlights.DetailHighlightRestaurant
        Picasso.get().load(highlights.ImageHighlightFood).into(imgFood)
        Picasso.get().load(highlights.LogoHighlightRestaurant).into(logoRes)
        cusi.text = highlights.CuisicuiHighLightRestaurant
        btnAdd.setOnClickListener {
            //            CurrentCartFragment.create(highlights.ImageHighlightFood,
//                highlights.NameHighlightFood,
//                highlights.PriceFood.value,
//                1,highlights.IdHighlightFood)
            (it.context as HomeActivity).list.add( Oders_Current_Cart(
                highlights.ImageHighlightFood,
                highlights.NameHighlightFood,
                highlights.PriceFood.value,
                1, highlights.IdHighlightFood
            ))
//            odersItem.add(
//                Oders_Current_Cart(
//                    highlights.ImageHighlightFood,
//                    highlights.NameHighlightFood,
//                    highlights.PriceFood.value,
//                    1, highlights.IdHighlightFood
//                )
//            )
            Log.d("BBB",odersItem.size.toString())


            //CurrentCartFragment.create(orderItem).arguments(this,list)



        }

    }
}