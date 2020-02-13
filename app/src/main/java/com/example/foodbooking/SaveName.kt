package com.example.foodbooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodbooking.apis.ApiService
import com.example.foodbooking.apis.requestModels.ProfileChangeRequest
import com.example.foodbooking.apis.responseModels.GetCurrentUserResponse
import com.example.foodbooking.apis.responseModels.ResponseProfileChange
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_save_name.*
import retrofit2.HttpException

class SaveName : AppCompatActivity() {

    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_name)

        imgback.setOnClickListener {
            finish()
        }
        val token = SettingService.Get(AppConstants.TOKENKEY, this)
        val userId = SettingService.Get(AppConstants.USER_ID, this)
        apiService.getCurrentUser("Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError)


        btnSaveName.setOnClickListener {
            val fistname = edFirstnamechange.text.toString()
            val lastname = edLastnamechange.text.toString()
            apiService.Changeprofile(
                ProfileChangeRequest(fistname,lastname,"" )
                , "Bearer ${token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseChangeProfile, this::handleError)

        }


    }


    fun handleResponseChangeProfile(getCurrentUserResponse: ResponseProfileChange) {
        Toast.makeText(this, "update success", Toast.LENGTH_LONG).show()
        getCurrentUserResponse.data.firstName
        getCurrentUserResponse.data.lastName
        finish()

    }


    fun handleResponse(getCurrentUserResponse: GetCurrentUserResponse) {

        edFirstnamechange.setText(getCurrentUserResponse.data.firstName)
        edLastnamechange.setText(getCurrentUserResponse.data.lastName)
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

        Toast.makeText(this, "Error ${message}", Toast.LENGTH_LONG).show()

    }

}
