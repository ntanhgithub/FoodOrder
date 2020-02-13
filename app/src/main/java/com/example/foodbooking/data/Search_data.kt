package com.example.foodbooking.data

class Search_data {
    lateinit var promotionSearch: ArrayList<promotionSearch>
    lateinit var addressSearch: String
    lateinit var nameSearch: String
    lateinit var priceSearch: String
    lateinit var imageSearch: String
    val cuisineSearch: String
    lateinit var deliveryId :Number

    constructor(
        proSearch: ArrayList<promotionSearch>,
        adresSearch: String,
        nameSearch: String,
        priceSearch: String,
        imageSearch: String,
        cuisineSearch: String,
        deliveryId: Number
    ) {
        this.promotionSearch = proSearch
        this.addressSearch = adresSearch
        this.nameSearch = nameSearch
        this.priceSearch = priceSearch
        this.imageSearch = imageSearch
        this.cuisineSearch = cuisineSearch
        this.deliveryId = deliveryId
    }
}

class promotionSearch(val textSearch: String, val imageSearch: String)