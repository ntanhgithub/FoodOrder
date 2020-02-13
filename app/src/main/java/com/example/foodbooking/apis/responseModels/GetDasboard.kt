package com.example.foodbooking.apis.responseModels

data class GetDasboardResponse(val data: DasboardResponse, val error: String, val code: Number)

data class DasboardResponse(
    val promotions: ArrayList<PromotionsDetail>,
    val foods: ArrayList<FoodsDetail>,
    val categories: ArrayList<CategoriesDetail>
)

data class PromotionsDetail(
    val promotionTitle: String,
    val address: String,
    val name: String,
    val deliveryId: Number,
    val code: String,
    val image: String,
    val cuisines: String
)

data class FoodsDetail(
    val name: String,
    val id: Number,
    val description: String,
    val price: PriceDetail,
    val image: String,
    val restaurantName: String,
    val restaurantImage: String,
    val restaurantCuisines: String
)

data class PriceDetail(val text: String, val unit: String, val value: Int)

data class CategoriesDetail(val category: String, val id: String, val url: String)