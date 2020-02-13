package com.example.foodbooking.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.foodbooking.AppConstants
import com.example.foodbooking.R
import com.example.foodbooking.SaveName
import com.example.foodbooking.SettingService
import com.example.foodbooking.apis.ApiService
import com.example.foodbooking.apis.responseModels.GetCurrentUserResponse
import com.example.foodbooking.apis.responseModels.LoginResponse
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.HttpException


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val token = SettingService.Get(AppConstants.TOKENKEY, this.activity as Activity)
        val rlaccount = view.findViewById<RelativeLayout>(R.id.rlAccount)
        rlaccount.setOnClickListener {
            val intent = Intent(this.activity, SaveName::class.java)
            startActivity(intent)



        }

        val userId: String =
            (SettingService.Get(AppConstants.USER_ID, this.activity as Activity)) as String


        apiService.getCurrentUser("Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError)


        val profileImage1 = view.findViewById<CircleImageView>(R.id.profile_image);

        Picasso.get()
            .load("https://images.foody.vn/res/g93/920647/prof/s640x400/foody-upload-api-foody-mobile-lklk-190522154211.jpg")
            .into(profileImage1);
        val logout = view.findViewById<RelativeLayout>(R.id.rlLogOut)
        logout.setOnClickListener {
            SettingService.Save(AppConstants.TOKENKEY,"",this.activity as Activity)
            SettingService.Save(AppConstants.USER_ID,"",this.activity as Activity)
            (this.activity as Activity).finish()
        }
        return view;
    }

    override fun onResume() {
        val token = SettingService.Get(AppConstants.TOKENKEY, this.activity as Activity)
        apiService.getCurrentUser("Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError)
        super.onResume()
    }
    fun handleResponse(getCurrentUserResponse: GetCurrentUserResponse) {
        txtUserName.text = getCurrentUserResponse.data.name


    }

    fun handleError(error: Throwable) {

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
