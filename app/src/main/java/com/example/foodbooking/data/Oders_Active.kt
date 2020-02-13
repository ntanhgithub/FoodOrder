package com.example.foodbooking.data

class Oders_Active {
    lateinit var ImageOrderActive: String
    var NameOrderActive: String
    var PriceOrderActive: Int
    var CountOrderACtive: Int
    var Total: Int = 0
    var IdFood: Number = 0

    constructor(nameOdersActive: String, countOrderActive: Int, priOrderActive: Int) {
        this.NameOrderActive = nameOdersActive
        this.CountOrderACtive = countOrderActive
        this.PriceOrderActive = priOrderActive
    }
}