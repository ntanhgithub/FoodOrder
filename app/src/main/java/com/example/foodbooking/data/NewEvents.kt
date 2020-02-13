package com.example.foodbooking.data


class NewEvents{
    lateinit var nEventName :String
    lateinit var nEventTime :String
    lateinit var nEventIcon :String

    constructor(neventName:String, neventTime:String){
        this.nEventName = neventName
        this.nEventTime = neventTime
    }
}