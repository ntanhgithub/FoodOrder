package com.example.foodbooking.fragment


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodbooking.AppConstants

import com.example.foodbooking.R
import com.example.foodbooking.SettingService
import com.example.foodbooking.adapter.DeliveryAdapter
import com.example.foodbooking.apis.ApiService
import com.example.foodbooking.apis.responseModels.GetCategories
import com.example.foodbooking.apis.responseModels.GetDelivery
import com.example.foodbooking.data.Delivery
import com.example.foodbooking.data.pricedeli
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_delivery.*
import retrofit2.HttpException

/**
 * A simple [Fragment] subclass.
 */
class DeliveryFragment : Fragment() {
    companion object {
        fun create(deliveryId: String, img :String,title:String): DeliveryFragment {
            val fragment = DeliveryFragment()
            fragment.deliveryId = deliveryId
            fragment.img = img
            fragment.deliveryTitle = title
            return fragment
        }
    }

    private val apiService by lazy {
        ApiService.create()
    }
    lateinit var deliveryId: String
    lateinit var img :String
    lateinit var deliveryTitle:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_delivery, container, false)
        val token = SettingService.Get(AppConstants.TOKENKEY, this.activity as Activity)
        val imgDeli = view.findViewById<ImageView>(R.id.imgDeliveryId)
        val deliTitle = view.findViewById<TextView>(R.id.tvtitleDeliveryId)
        val back = view.findViewById<ImageView>(R.id.imgBack)
        back.setOnClickListener { (it.context as AppCompatActivity).supportFragmentManager.popBackStack() }
        Picasso.get().load(img).into(imgDeli)
        deliTitle.text = deliveryTitle
        apiService.getDeliveryId(deliveryId, "Bearer ${token}")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { progress_delivery.visibility = View.GONE }
            .subscribe(this::handleDeliveryResponse, this::handleError)

        return view
    }

    fun handleDeliveryResponse(getDelivery: GetDelivery) {
        val deli = ArrayList<Delivery>()
        for (item in getDelivery.data) {
            val priceDeli = pricedeli(item.price.text,item.price.unit,item.price.value)
            deli.add(Delivery(item.name, item.id, item.description, priceDeli, item.image))
        }
        this.recyclerview_delivery.layoutManager = LinearLayoutManager(this.activity)
        this.recyclerview_delivery.adapter = DeliveryAdapter(deli,this.activity)
    }


    fun handleError(error: Throwable) {
        var message = "An error occurred"
        if (error is HttpException) {
            // Kotlin will smart cast at this point
            val errorJsonString = error.response().errorBody()?.string()
            val gson = GsonBuilder().setPrettyPrinting().create()
            val loginresponse = gson.fromJson(errorJsonString, GetCategories::class.java)
            message = loginresponse.error

        } else {
            message = error.message ?: message
        }
        Toast.makeText(this.activity, "Error ${message}", Toast.LENGTH_LONG).show()

    }


}
