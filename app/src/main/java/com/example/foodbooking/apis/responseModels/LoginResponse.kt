package com.example.foodbooking.apis.responseModels

data class LoginResponse(val data: LoginToken, val error: String, val code: Number)

data class LoginToken(val access_token: String, val email: String, val user_id: String);