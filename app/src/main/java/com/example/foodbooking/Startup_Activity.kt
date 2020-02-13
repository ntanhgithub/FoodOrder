package com.example.foodbooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Startup_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        SettingService.Save(AppConstants.TOKENKEY, "", this)

        if (!SettingService.Get(AppConstants.TOKENKEY, this).isNullOrEmpty()) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}
