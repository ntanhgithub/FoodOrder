package com.example.foodbooking

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodbooking.apis.ApiRequestInterface
import com.example.foodbooking.apis.ApiService
import com.example.foodbooking.apis.requestModels.LoginRequest
import com.example.foodbooking.apis.responseModels.GetCurrentUserResponse
import com.example.foodbooking.apis.responseModels.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = edPassword.text.toString()
            if (email.isEmpty()|| password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                apiService.login(LoginRequest(email, password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { pbLoading.visibility = View.VISIBLE }
                    .doOnComplete { pbLoading.visibility = View.GONE }
                    .subscribe(this::handleResponse, this::handleError)

            }


        }
        btnRegisterLogin.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    fun handleResponse(loginResponse: LoginResponse) {
        val token = loginResponse.data.access_token;
        val userId = loginResponse.data.user_id;

        SettingService.Save(AppConstants.TOKENKEY, token, this)
        SettingService.Save(AppConstants.USER_ID, userId, this)


        apiService.getCurrentUser("Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doOnComplete { hideLoading() }
            .subscribe(this::handleGetUserResponse, this::handleGetUserError)


    }

    fun handleError(error: Throwable) {

        var message = "An error occurred"
        val code: Number
        if (error is HttpException) {
            val errorJsonString = error.response().errorBody()?.string()
            val loginResponse =
                ApiService.createGson().fromJson(errorJsonString, LoginResponse::class.java)
            message = loginResponse.error
            code = loginResponse.code

        } else {
            message = error.message ?: message
        }
        if (message == "email or password is incorrect." || message == "Email is invalid." || message == "User not found.") {

            hideLoading()
        }
        Toast.makeText(this, "Error ${message}", Toast.LENGTH_LONG).show()
    }

    fun handleGetUserResponse(getCurrentUserResponse: GetCurrentUserResponse) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("MyName","Thuan")
        intent.putExtra("Tuoi", 24)
        startActivity(intent)
    }

    fun handleGetUserError(error: Throwable) {

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

    fun showLoading() {
        pbLoading.visibility = View.VISIBLE;
    }

    fun hideLoading() {
        pbLoading.visibility = View.GONE;
    }
}
