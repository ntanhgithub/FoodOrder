package com.example.foodbooking.fragment


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodbooking.AppConstants

import com.example.foodbooking.R
import com.example.foodbooking.SettingService
import com.example.foodbooking.adapter.SearchAdapter
import com.example.foodbooking.apis.ApiService
import com.example.foodbooking.apis.responseModels.GetCategories
import com.example.foodbooking.apis.responseModels.GetDataSearch
import com.example.foodbooking.data.Search_data
import com.example.foodbooking.data.promotionSearch
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home_search.*
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.HttpException

/**
 * A simple [Fragment] subclass.
 */
class HomeSearchFragment : Fragment() {
    companion object {
        fun create(keyword: String): HomeSearchFragment {
            val fragment = HomeSearchFragment()
            fragment.Keyword = keyword
            return fragment
        }
    }

    private val apiService by lazy {
        ApiService.create()
    }
    lateinit var Keyword: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_search, container, false)
        val token = SettingService.Get(AppConstants.TOKENKEY, context as Activity)
        val process= view.findViewById<ProgressBar>(R.id.progressSearchHome)
        val close  = view.findViewById<ImageView>(R.id.imgClose)
        apiService.getDataSearch(Keyword, "Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { process.visibility = View.VISIBLE }
            .doOnComplete { process.visibility = View.GONE }
            .subscribe(this::handleSearchResponse, this::handleError)
        close.setOnClickListener {
            (it.context as AppCompatActivity).supportFragmentManager.popBackStack()
        }
        return view
    }

    fun handleSearchResponse(getDataSearch: GetDataSearch) {
        val search = ArrayList<Search_data>()

        for (itemPro in getDataSearch.data) {
            val promo = ArrayList<promotionSearch>()

            for (pro in itemPro.promotions) {
                promo.add(promotionSearch(pro.text, pro.image))
            }
            search.add(
                Search_data(
                    promo,
                    itemPro.address,
                    itemPro.name,
                    itemPro.priceRange,
                    itemPro.image,
                    itemPro.cuisines,
                    itemPro.deliveryId
                )
            )

        }
        this.recyclerview_SearchHome.layoutManager = LinearLayoutManager(this.activity)
        this.recyclerview_SearchHome.adapter = SearchAdapter(search, this.activity)
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
