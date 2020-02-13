package com.example.foodbooking.data

class Categories_food{
    lateinit var ImgCategories :String
    lateinit var CategoriesName :String
    lateinit var Id :String


    constructor( categoriesName :String, imgCategories :String, id:String){
        this.CategoriesName = categoriesName
        this.ImgCategories = imgCategories
        this.Id = id
    }
}