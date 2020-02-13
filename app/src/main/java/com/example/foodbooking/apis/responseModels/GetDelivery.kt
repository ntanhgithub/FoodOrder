package com.example.foodbooking.apis.responseModels

data class GetDelivery(val data:ArrayList<deliDetail>,val error:String,val code: Number)

data class deliDetail(val name:String,val id:Number,val description:String,val price:pri,val image:String)

data class pri(val text:String, val unit:String, val value:Int)