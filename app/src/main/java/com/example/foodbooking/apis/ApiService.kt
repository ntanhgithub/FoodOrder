package com.example.foodbooking.apis

import com.example.foodbooking.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiService {
    companion object {
        fun create(): ApiRequestInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(AppConstants.API_URL_BASE)
                .build()
                .create(ApiRequestInterface::class.java);

            return retrofit
        }

        fun createGson() : Gson {
            return GsonBuilder().setPrettyPrinting().create();
        }
    }
}


