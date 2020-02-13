package com.example.foodbooking.apis.responseModels

data class GetCurrentUserResponse(val data: UserResponse, val error: String, val code: Number);

data class UserResponse(val name: String, val firstName : String, val lastName :String, val email: String, val avatar: String, val phone: String, val address: String);