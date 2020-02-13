package com.example.foodbooking.data

class Plan_food{
     var ImagePlanFood :String
     var NamePlanFood :String
     var DetailPlanFood :String
     var CuisicesPlan :String
     var AddressPlan :String
     var CodePlan :String
     var DeliveryIdPlan:Number

    constructor(imgPlan:String,nameFood :String, detailFood :String, cuisices:String, addressPlan:String, code:String,idPlan:Number){
        this.ImagePlanFood = imgPlan
        this.NamePlanFood = nameFood
        this.DetailPlanFood = detailFood
        this.CuisicesPlan = cuisices
        this.AddressPlan = addressPlan
        this.CodePlan = code
        this.DeliveryIdPlan = idPlan


    }
}