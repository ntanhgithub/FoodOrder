package com.example.foodbooking.apis.responseModels

data class ResponseNotifications(val data:ArrayList<NotificationResponse>, val error:String, val code:Number)

data class NotificationResponse(val message:String, val icon:String, val uuid: Number, val createdDate:String)