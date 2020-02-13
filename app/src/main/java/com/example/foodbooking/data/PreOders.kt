package com.example.foodbooking.data

class PreOrders{
    lateinit var PreId: String
    lateinit var PreTime: String
    lateinit var PreDetail: String
    lateinit var PreCost: String

    constructor(preId :String, preTime:String, preDetail:String, preCost:String){
        this.PreId = preId
        this.PreTime = preTime
        this.PreDetail = preDetail
        this.PreCost = preCost
    }
}