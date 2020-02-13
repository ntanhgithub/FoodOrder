package com.example.foodbooking.data

class Delivery{
    lateinit var DeliveryName:String
    lateinit var DeliveryId :Number
    lateinit var DeliveryDescription:String
    lateinit var DeliveryPrice:pricedeli
    lateinit var DeliveryImage:String

    constructor(
        DeliveryName: String,
        DeliveryId: Number,
        DeliveryDescription: String,
        DeliveryPrice: pricedeli,
        DeliveryImage: String
    ) {
        this.DeliveryName = DeliveryName
        this.DeliveryId = DeliveryId
        this.DeliveryDescription = DeliveryDescription
        this.DeliveryPrice = DeliveryPrice
        this.DeliveryImage = DeliveryImage
    }
}
class pricedeli(val text:String,val unit:String,val value:Int)