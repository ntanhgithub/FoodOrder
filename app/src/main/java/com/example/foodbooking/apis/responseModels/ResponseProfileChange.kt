package com.example.foodbooking.apis.responseModels

data class ResponseProfileChange(val data:UserNameResponse, val error:String, val code:Number)

data class  UserNameResponse(val name: String, val firstName : String, val lastName :String, val email: String, val avatar: String, val phone: String, val address: String);