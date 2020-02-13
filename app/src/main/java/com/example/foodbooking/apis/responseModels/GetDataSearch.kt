package com.example.foodbooking.apis.responseModels

data class GetDataSearch(val data:ArrayList<DataResponse>,val error:Number,val code:Number)
data class DataResponse(val promotions: ArrayList<promo>, val address:String, val name:String, val deliveryId:Number,val priceRange:String,val image:String,val cuisines:String)


data class promo(val text:String, val image: String)

