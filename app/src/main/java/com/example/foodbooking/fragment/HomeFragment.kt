package com.example.foodbooking.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodbooking.AppConstants
import com.example.foodbooking.MainActivity
import com.example.foodbooking.R
import com.example.foodbooking.SettingService
import com.example.foodbooking.adapter.CategoriesAdapter
import com.example.foodbooking.adapter.HighlightAdapter
import com.example.foodbooking.adapter.PlanAdapter
import com.example.foodbooking.adapter.SearchAdapter
import com.example.foodbooking.apis.ApiService
import com.example.foodbooking.apis.responseModels.GetDasboardResponse
import com.example.foodbooking.apis.responseModels.GetDataSearch
import com.example.foodbooking.data.*
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_search.*
import retrofit2.HttpException

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

       // SettingService.Save(AppConstants.TOKENKEY,"",this.activity as Activity)
        val token = SettingService.Get(AppConstants.TOKENKEY, this.activity as Activity)
        val edSearchHome = view.findViewById<EditText>(R.id.edSearchHome)
        val btSearch = view.findViewById<Button>(R.id.btSearchHome)
        btSearch.setOnClickListener {
            val keyword = edSearchHome.text.toString()
            if (keyword.isEmpty()) {

            } else {

                (this.activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.flSearch,
                        HomeSearchFragment.create(keyword)
                    ).addToBackStack(null).commit()


            }
        }
        apiService.getDashBoard("Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { hideloading() }
            .subscribe(this::handleDashBoardResponse, this::handleError)
        return view
    }

    fun handleDashBoardResponse(getDasboard: GetDasboardResponse) {
        //highlight
        val highlightFood = ArrayList<HighLight_food>()
        for (itemHL in getDasboard.data.foods) {
            val item = priceHighliht(itemHL.price.text,itemHL.price.unit,itemHL.price.value)
            highlightFood.add(
                HighLight_food(
                    itemHL.image,
                    itemHL.name,
                    item,
                    itemHL.restaurantImage,
                    itemHL.restaurantName,
                    itemHL.description,
                    itemHL.restaurantCuisines,
                    itemHL.id
                )
            )
        }
        recyclerview_highlight.layoutManager = LinearLayoutManager(this.activity)
        recyclerview_highlight.adapter = HighlightAdapter(highlightFood, this.activity)
        //plant
        val planFood = ArrayList<Plan_food>()
        for (itemPlan in getDasboard.data.promotions) {
            planFood.add(
                Plan_food(
                    itemPlan.image,
                    itemPlan.name,
                    itemPlan.promotionTitle,
                    itemPlan.cuisines,
                    itemPlan.address, itemPlan.code, itemPlan.deliveryId
                )
            )
        }
        recyclerview_plant.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerview_plant.adapter = PlanAdapter(planFood, this.activity)
        //categories
        val category = ArrayList<Categories_food>()
        for (item in getDasboard.data.categories) {
            category.add(Categories_food(item.category, item.url, item.id))
        }
        recyclerview_categories.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerview_categories.adapter = CategoriesAdapter(category, this.activity)

    }

    fun handleError(error: Throwable) {
        var message = "An error occurred"
        if (error is HttpException) {
            // Kotlin will smart cast at this point
            val errorJsonString = error.response().errorBody()?.string()
            val gson = GsonBuilder().setPrettyPrinting().create()
            val loginresponse = gson.fromJson(errorJsonString, GetDasboardResponse::class.java);
            message = loginresponse.error

        } else {
            message = error.message ?: message
        }
        if (message == "Unauthorized") {
            Toast.makeText(this.activity, "Phiên đăng nhập đã hết hạn", Toast.LENGTH_LONG)
                .show()
            SettingService.Save(AppConstants.TOKENKEY, "", this.activity as Activity)
            this.activity?.finish()
           val  intent = Intent(this.context,MainActivity::class.java)
            startActivity(intent)
        }
//        else {
//            Toast.makeText(this.activity, "Error ${message}", Toast.LENGTH_LONG).show()
//        }
    }

    fun showloading() {
        pbLoadingHome.visibility = View.VISIBLE
    }

    fun hideloading() {
        pbLoadingHome.visibility = View.GONE
    }
}
