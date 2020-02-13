package com.example.foodbooking.apis


import com.example.foodbooking.apis.requestModels.LoginRequest
import com.example.foodbooking.apis.requestModels.ProfileChangeRequest
import com.example.foodbooking.apis.requestModels.RegisterRequest
import com.example.foodbooking.apis.responseModels.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiRequestInterface {

    @POST("user/login")
    fun login(@Body request: LoginRequest): Observable<LoginResponse>

    @GET("user/current")
    fun getCurrentUser( @Header("Authorization") token: String) : Observable<GetCurrentUserResponse>


    @POST("user/register")
    fun Register(@Body register : RegisterRequest) : Observable<RegisterResponse>


    @GET("home/dashboard")
    fun getDashBoard(@Header("Authorization") token: String): Observable<GetDasboardResponse>

    @PUT("user/current")
    fun Changeprofile(@Body changeprofile : ProfileChangeRequest,@Header("Authorization") token: String): Observable<ResponseProfileChange>


    @GET("notifications/promotions")
    fun getpromotion(@Header("Authorization") token: String): Observable<ResponseNotifications>

    @GET("/api/home/categories/{categoryId}")
    fun getCategories(@Path("categoryId") categoryId:String,  @Header("Authorization") token: String):Observable<GetCategories>

    @GET(   "/api/home/search")
    fun getDataSearch(@Query ("keyword")keyword:String,@Header("Authorization") token :String): Observable<GetDataSearch>

    @GET("/api/home/menu/{deliveryId}")
    fun getDeliveryId(@Path("deliveryId") deliveryId:String,@Header("Authorization") token :String): Observable<GetDelivery>

}

