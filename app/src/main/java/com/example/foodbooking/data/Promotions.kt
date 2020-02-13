package com.example.foodbooking.data

class Promotions{
    lateinit var PrtionName : String
    lateinit var PrtionTime :String
    lateinit var PrtionIcon : String

    constructor(prtionsName: String, prtionTime: String, prtionicon:String){
        this.PrtionName = prtionsName
        this.PrtionTime = prtionTime
        this.PrtionIcon = prtionicon
    }

}

