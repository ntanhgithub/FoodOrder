package com.example.foodbooking.data
class ItemOders(val item : ArrayList<Oders_Current_Cart>)
class Oders_Current_Cart {
     var ImageCurrenCart: String
     var NameCurrenCart: String
    var PriCurrenCart: Int = 0
    var CountCurrenCart: Int = 0
    var IdCurrenCart : Number =0
    var Total = PriCurrenCart * CountCurrenCart

    constructor(img:String,nameCurrenCart: String, priCurrenCart: Int,count :Int,id:Number) {
        this.NameCurrenCart = nameCurrenCart
        this.PriCurrenCart = priCurrenCart
        this.ImageCurrenCart = img
        this.CountCurrenCart = count
        this.IdCurrenCart = id
    }
}