package com.example.foodbooking.data


class Oder{
    lateinit var OderName : String
    lateinit var Time : String
    lateinit var icon : String

    constructor(oderName:String, time:String){
        this.OderName = oderName
        this.Time = time
    }
}
