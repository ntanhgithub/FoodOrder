package com.example.foodbooking.data

class LoginRequest(){
    lateinit var email:String
    lateinit var password:String
}

class LoginResponse(){
    lateinit var data : LoginToken
    lateinit var error : String
    lateinit var code : Number

}

class LoginToken(){
    lateinit var accessToken: String
    lateinit var email: String
    lateinit var userid :String

}