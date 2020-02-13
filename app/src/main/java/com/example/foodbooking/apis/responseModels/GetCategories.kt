package com.example.foodbooking.apis.responseModels

data class GetCategories(val data: CategoriesResponse, val error: String, val code: Number)

data class CategoriesResponse(val restaurants: ArrayList<RestaurantResponse>, val foods: ArrayList<FoodsCate>)

data class RestaurantResponse(
    val promotions: ArrayList<Discount>,
    val address: String,
    val name: String,
    val deliveryId: Number,
    val priceRange: String,
    val image: String,
    val cuisines: String
)

data class Discount(val text:String, val image: String)

data class FoodsCate(
    val name: String,
    val id: Number,
    val description: String,
    val price: PriceCate,
    val image: String,
    val restaurantName: String,
    val restaurantImage: String,
    val restaurantCuisines: String
)

data class PriceCate(val text: String, val unit: String, val value: Int)