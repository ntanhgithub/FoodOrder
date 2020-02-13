package com.example.foodbooking

import android.app.Activity
import android.content.SharedPreferences

class SettingService {
    companion object{
        fun Save(key: String, value: String, activity: Activity){
            val sharedPref: SharedPreferences = activity.getSharedPreferences(key, 0)
            val editor = sharedPref.edit()
            editor.putString(AppConstants.TOKENKEY, value)
            editor.apply()
        }
        fun Get(key: String,activity: Activity): String? {
            val sharedPref: SharedPreferences = activity.getSharedPreferences(key, 0)
            return sharedPref.getString(key, "");
        }
    }
}