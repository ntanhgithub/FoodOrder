package com.example.foodbooking.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.foodbooking.AppConstants
import com.example.foodbooking.R
import com.example.foodbooking.SettingService
import com.example.foodbooking.adapter.PrtionAdapter
import com.example.foodbooking.apis.ApiService
import com.example.foodbooking.apis.responseModels.GetCurrentUserResponse
import com.example.foodbooking.apis.responseModels.ResponseNotifications
import com.example.foodbooking.data.Promotions
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_promotion.*
import retrofit2.HttpException


/**
 * A simple [Fragment] subclass.
 */
class PromotionFragment : Fragment() {


    private val apiService by lazy {
        ApiService.create()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_promotion, container, false)
        val token = SettingService.Get(AppConstants.TOKENKEY, this.activity as Activity)

        // Current thread -> IO -> IO -> Main -> Main -> Main
        apiService.getpromotion("Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError)




        return view


    }

    fun handleResponse(getpromotions: ResponseNotifications){
        val promotion = ArrayList<Promotions>()
        for(item in getpromotions.data)
        {
            promotion.add(Promotions(item.message,item.createdDate,item.icon))
        }
        rvPrtion.layoutManager = LinearLayoutManager(this.activity)
        rvPrtion.adapter = PrtionAdapter(promotion,this.activity)

    }

    fun handleError(error:Throwable){
        var message = "An error occurred"

        if (error is HttpException) {
            val errorJsonString = error.response().errorBody()?.string()
            val loginResponse = ApiService.createGson()
                .fromJson(errorJsonString, GetCurrentUserResponse::class.java)
            message = loginResponse.error

        } else {
            message = error.message ?: message
        }

        Toast.makeText(this.activity, "Error ${message}", Toast.LENGTH_LONG).show()
    }
}
