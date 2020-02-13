package com.example.foodbooking.fragment


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodbooking.AppConstants
import com.example.foodbooking.R
import com.example.foodbooking.SettingService
import com.example.foodbooking.adapter.ComboAdapter
import com.example.foodbooking.adapter.HighlightAdapter
import com.example.foodbooking.apis.ApiService
import com.example.foodbooking.apis.responseModels.GetCategories
import com.example.foodbooking.data.Combo_food
import com.example.foodbooking.data.HighLight_food
import com.example.foodbooking.data.Promotion
import com.example.foodbooking.data.priceHighliht
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_category.*
import retrofit2.HttpException

/**
 * A simple [Fragment] subclass.
 */
private val apiService by lazy {
    ApiService.create()
}

class CategoryFragment : Fragment() {
    companion object {
        fun create(cateId: String, cateName: String): CategoryFragment {
            val fragment = CategoryFragment()
            fragment.cateId = cateId
            fragment.cateName = cateName
            return fragment
        }
    }

    lateinit var cateId: String
    lateinit var cateName: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        // Inflate the layout for this fragment
        val tvcate = view.findViewById<TextView>(R.id.tvCatename)
        val lnBack = view.findViewById<LinearLayout>(R.id.linearBack)

        tvcate.text = cateName
        lnBack.setOnClickListener {
            (it.context as AppCompatActivity).supportFragmentManager.popBackStack()
        }

        return view
    }

    override fun onResume() {

        val token = SettingService.Get(AppConstants.TOKENKEY, context as Activity)
        apiService.getCategories(cateId, "Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { progress_category.visibility = View.GONE }
            .subscribe(this::handleCategoryResponse, this::handleError)

        super.onResume()
    }
    fun handleCategoryResponse(getCategories: GetCategories) {

        val promotion = ArrayList<Combo_food>()

        for (itemPro in getCategories.data.restaurants) {
            val distcount = ArrayList<Promotion>()
            for (item in itemPro.promotions) {
                distcount.add(Promotion(item.image, item.text))
            }
            promotion.add(
                Combo_food(
                    itemPro.image,
                    itemPro.name,
                    itemPro.priceRange, distcount, itemPro.address, itemPro.cuisines,itemPro.deliveryId
                )
            )

        }
        this.recyclerview_promotion.layoutManager = LinearLayoutManager(this.activity)
        this.recyclerview_promotion.adapter = ComboAdapter(promotion, this.activity)

        val foods = ArrayList<HighLight_food>()
        for (itemCate in getCategories.data.foods) {
            val pri = priceHighliht(itemCate.price.text,itemCate.price.unit,itemCate.price.value)
            foods.add(
                HighLight_food(
                    itemCate.image,
                    itemCate.name,
                    pri,itemCate.restaurantImage,
                    itemCate.restaurantName,
                    itemCate.description,
                    itemCate.restaurantCuisines,
                    itemCate.id
                )
            )
        }
        this.recyclerview_categories_food.layoutManager = LinearLayoutManager(this.activity)
        this.recyclerview_categories_food.adapter = HighlightAdapter(foods,this.activity)
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
