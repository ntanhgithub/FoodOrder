package com.example.foodbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.data.Oders_Current_Cart
import com.squareup.picasso.Picasso


class OdersCurrentAdapter(val cr:List<Oders_Current_Cart>, val context: Context?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_currentcart,parent,false)
        return OdersCurrentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cr.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentCunrrentCart =  cr[position]
        (holder as OdersCurrentViewHolder).bindData(currentCunrrentCart)
    }

}
class  OdersCurrentViewHolder( val view: View) : RecyclerView.ViewHolder(view) {
    val imgCurrent = view.findViewById<ImageView>(R.id.imgOdersCurrentCart)
    val tvCurrentName = view.findViewById<TextView>(R.id.tvOdersCurrentName)
    val tvCurrentCost = view.findViewById<TextView>(R.id.tvOdersCurrentCost)
    val tvCurrentCount = view.findViewById<TextView>(R.id.tvOdersCurrentCount)
    val tvCurrentPri = view.findViewById<TextView>(R.id.tvPriceOdersCurrent)
    val btAdd = view.findViewById<Button>(R.id.btnOdersCurrentAdd)
    val btSub = view.findViewById<Button>(R.id.btnOdersCurrentSub)
    fun bindData(cr : Oders_Current_Cart){
        tvCurrentName.text = cr.NameCurrenCart
        tvCurrentCost.text = cr.PriCurrenCart.toString()
        Picasso.get().load(cr.ImageCurrenCart).into(imgCurrent)
        tvCurrentPri.setText("${cr.PriCurrenCart} * ${cr.CountCurrenCart}")
        btAdd.setOnClickListener {
            cr.CountCurrenCart += 1
            val total = cr.CountCurrenCart * cr.PriCurrenCart
            tvCurrentPri.setText("${cr.PriCurrenCart} * ${cr.CountCurrenCart}")
            tvCurrentCount.text = cr.CountCurrenCart.toString()
            tvCurrentCost.text = total.toString()
        }

        btSub.setOnClickListener {
            val total = cr.CountCurrenCart * cr.PriCurrenCart
            cr.CountCurrenCart -= 1
            if(cr.CountCurrenCart < 0){
                cr.CountCurrenCart = 0
            }
            tvCurrentPri.setText("${cr.PriCurrenCart} * ${cr.CountCurrenCart}")
            tvCurrentCost.text = total.toString()
            tvCurrentCount.text = cr.CountCurrenCart.toString()
        }
        tvCurrentCount.text = cr.CountCurrenCart.toString()

    }


}