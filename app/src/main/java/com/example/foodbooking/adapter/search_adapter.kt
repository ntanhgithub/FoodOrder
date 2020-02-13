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
import com.example.foodbooking.data.Search_data
import com.example.foodbooking.fragment.DeliveryFragment
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SearchAdapter(val search:List<Search_data>,val context : Context?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search_food,parent,false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return search.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = search[position]
        (holder as SearchViewHolder).bindData(current)
    }
}
class SearchViewHolder( val view: View) : RecyclerView.ViewHolder(view) {
    val namecate = view.findViewById<TextView>(R.id.tvSearchName)
    val img = view.findViewById<ImageView>(R.id.imgSearch)
    val price= view.findViewById<TextView>(R.id.tvSearchPrice)
    val icon1 = view.findViewById<ImageView>(R.id.icSearch1)
    val icon2 = view.findViewById<ImageView>(R.id.icSearch2)
    val icon3 = view.findViewById<ImageView>(R.id.icSearch3)
    val text1 = view.findViewById<TextView>(R.id.tvSearch1)
    val text2 = view.findViewById<TextView>(R.id.tvSearch2)
    val text3 = view.findViewById<TextView>(R.id.tvSearch3)
    val address = view.findViewById<TextView>(R.id.tvSearchAddress)
    val cusecui = view.findViewById<TextView>(R.id.tvSearchCusecuis)


    fun bindData(search : Search_data){
        namecate.text = search.nameSearch
        Picasso.get().load(search.imageSearch).into(img)
        price.text = search.priceSearch
        address.text = search.addressSearch
        cusecui.text = search.cuisineSearch
        if(search.promotionSearch.size == 1){
            Picasso.get().load(search.promotionSearch[0].imageSearch).into(icon1)
            text1.text = search.promotionSearch[0].textSearch
        }else if(search.promotionSearch.size == 2){
            Picasso.get().load(search.promotionSearch[0].imageSearch).into(icon1)
            text1.text = search.promotionSearch[0].textSearch
            Picasso.get().load(search.promotionSearch[1].imageSearch).into(icon2)
            text2.text = search.promotionSearch[1].textSearch
        }else if(search.promotionSearch.size == 2){
            Picasso.get().load(search.promotionSearch[0].imageSearch).into(icon1)
            text1.text = search.promotionSearch[0].textSearch
            Picasso.get().load(search.promotionSearch[1].imageSearch).into(icon2)
            text2.text = search.promotionSearch[1].textSearch
            Picasso.get().load(search.promotionSearch[2].imageSearch).into(icon3)
            text3.text = search.promotionSearch[2].textSearch
        }
        view.setOnClickListener {
            (it.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.flHome,  DeliveryFragment.create(search.deliveryId.toString(),search.imageSearch,search.nameSearch)).addToBackStack(null).commit()
        }
    }
}