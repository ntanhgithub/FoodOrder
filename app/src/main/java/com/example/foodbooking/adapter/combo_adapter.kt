package com.example.foodbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.data.Combo_food
import com.example.foodbooking.fragment.DeliveryFragment
import com.squareup.picasso.Picasso


class ComboAdapter(val cb: List<Combo_food>, val context: Context?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.combo_item, parent, false)
        return ComboViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cb.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentCombo_food = cb[position]
        (holder as ComboViewHolder).bindData(currentCombo_food)
    }
}

class ComboViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val imgProCate = view.findViewById<ImageView>(R.id.imgProCategory)
    val nameProCate = view.findViewById<TextView>(R.id.tvProName)
    val priceProCate = view.findViewById<TextView>(R.id.tvProCost)
    val icon1 = view.findViewById<ImageView>(R.id.icon_pro1)
    val icon2 = view.findViewById<ImageView>(R.id.icon_pro2)
    val icon3 = view.findViewById<ImageView>(R.id.icon_pro3)
    val discount1 = view.findViewById<TextView>(R.id.tvDiscount1)
    val discount2 = view.findViewById<TextView>(R.id.tvDiscount2)
    val discount3 = view.findViewById<TextView>(R.id.tvDiscount3)
    val addressProCate = view.findViewById<TextView>(R.id.tvProAddress)
    val cusineProCate = view.findViewById<TextView>(R.id.tvProCusines)


    fun bindData(cb: Combo_food) {

        Picasso.get().load(cb.ImagePro).into(imgProCate)
        nameProCate.text = cb.NamePro
        priceProCate.text = cb.CostPro
        addressProCate.text = cb.AddressPro
        cusineProCate.text = cb.CusinesPro
        if(cb.Promotions.size == 1){
            Picasso.get().load(cb.Promotions[0].icon).into(icon1)
            discount1.text = cb.Promotions[0].distcount
        }else if(cb.Promotions.size == 2){
            Picasso.get().load(cb.Promotions[0].icon).into(icon1)
            discount1.text = cb.Promotions[0].distcount
            Picasso.get().load(cb.Promotions[1].icon).into(icon2)
            discount2.text = cb.Promotions[1].distcount
        }else if(cb.Promotions.size == 2){
            Picasso.get().load(cb.Promotions[0].icon).into(icon1)
            discount1.text = cb.Promotions[0].distcount
            Picasso.get().load(cb.Promotions[1].icon).into(icon2)
            discount2.text = cb.Promotions[1].distcount
            Picasso.get().load(cb.Promotions[2].icon).into(icon3)
            discount3.text = cb.Promotions[2].distcount
        }

        view.setOnClickListener {
            (it.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.flHome,  DeliveryFragment.create(cb.deliveryId.toString(),cb.ImagePro,cb.NamePro)).addToBackStack(null).commit()
        }


    }
}