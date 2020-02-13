package com.example.foodbooking.data

class Combo_food {
    lateinit var ImagePro: String
    lateinit var NamePro: String
    lateinit var CostPro: String
    lateinit var Promotions: ArrayList<Promotion>
    lateinit var AddressPro: String
    lateinit var CusinesPro: String
    lateinit var deliveryId:Number

    constructor(
        imagePro: String,
        namePro: String,
        costPro: String,
        promotions: ArrayList<Promotion>,
        addressPro: String,
        cusinesPro: String,
        IdPro:Number
    ) {
        this.ImagePro = imagePro
        this.NamePro = namePro
        this.AddressPro = addressPro
        this.CostPro = costPro
        this.Promotions = promotions
        this.CusinesPro = cusinesPro
        this.deliveryId = IdPro

    }
}

class Promotion(val icon: String, val distcount: String)