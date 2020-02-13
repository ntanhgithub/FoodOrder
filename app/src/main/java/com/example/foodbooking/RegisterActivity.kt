package com.example.foodbooking

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodbooking.apis.ApiService
import com.example.foodbooking.apis.requestModels.RegisterRequest
import com.example.foodbooking.apis.responseModels.RegisterResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.HttpException

class RegisterActivity : AppCompatActivity() {

    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val edfirstname = findViewById<EditText>(R.id.edFirstname)
        val edlasname = findViewById<EditText>(R.id.edLastname)
        val edconfirm = findViewById<EditText>(R.id.edConfirmPass)
        val edEmail = findViewById<EditText>(R.id.edemailRegister)
        val edPass = findViewById<EditText>(R.id.edpasswordRegister)
        val btnregister = findViewById<Button>(R.id.btnRegister)
        btnregister.setOnClickListener {

            val email = edEmail.text.toString()
            val password = edPass.text.toString()
            val firstName = edfirstname.text.toString()
            val lastName = edlasname.text.toString()
            val edconfirn = edconfirm.text.toString()
            if (firstName.isEmpty() || lastName.isEmpty() || edconfirn.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Do not leave a blank", Toast.LENGTH_LONG).show()
            } else if (edConfirmPass.text.toString() != edpasswordRegister.text.toString()) {
                Toast.makeText(this, "Retype password incorrect", Toast.LENGTH_LONG).show()
            } else {
                apiService.Register(RegisterRequest(email, password, firstName, lastName))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { pbLoading1.visibility = View.VISIBLE }
                    .doOnComplete { pbLoading1.visibility = View.GONE }
                    .subscribe(this::handleResonse, this::handleError)
            }

        }


    }

    fun handleResonse(registerResponse: RegisterResponse) {
        Toast.makeText(this, registerResponse.data, Toast.LENGTH_LONG).show()
        finish();

    }

    fun handleError(error: Throwable) {
        var message = "An error occurred"
        if (error is HttpException) {
            val errorJsonString = error.response().errorBody()?.string()
            val registerresponse =
                ApiService.createGson().fromJson(errorJsonString, RegisterResponse::class.java)
            message = registerresponse.error

        } else {
            message = error.message ?: message
        }

        Toast.makeText(this, "Error ${message}", Toast.LENGTH_LONG).show()
    }


}

