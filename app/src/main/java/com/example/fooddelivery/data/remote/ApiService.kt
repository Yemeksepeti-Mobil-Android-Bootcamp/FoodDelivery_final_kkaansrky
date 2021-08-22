package com.example.fooddelivery.data.remote

import com.example.fooddelivery.data.entity.SuccessResponse
import com.example.fooddelivery.data.entity.order.OrdersRequest
import com.example.fooddelivery.data.entity.login.LoginRequest
import com.example.fooddelivery.data.entity.login.LoginResponse
import com.example.fooddelivery.data.entity.meal.MealResponse
import com.example.fooddelivery.data.entity.mealadd.MealAddRequest
import com.example.fooddelivery.data.entity.register.RegisterRequest
import com.example.fooddelivery.data.entity.register.RegisterResponse
import com.example.fooddelivery.data.entity.restaurant.RestaurantListResponse
import com.example.fooddelivery.data.entity.restaurant.RestaurantResponse
import com.example.fooddelivery.data.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddelivery.data.entity.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("a/order/bulk")
    suspend fun postOrders(@Body request: OrdersRequest): Response<SuccessResponse>

    @POST("a/restaurant")
    suspend fun postRestaurant(@Body request: RestaurantAddRequest): Response<SuccessResponse>

    @POST("a/restaurant/{restaurantId}/meal")
    suspend fun postMeal(
        @Path("restaurantId") restaurantId: String,
        @Body request: MealAddRequest
    ): Response<SuccessResponse>

    @GET("auth/profile")
    suspend fun getUser() : Response<UserResponse>

    @GET("a/restaurant")
    suspend fun getRestaurants(): Response<RestaurantListResponse>

    @GET("a/restaurant/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): Response<RestaurantResponse>

    @GET("a/restaurant/cuisine/{cuisineName}")
    suspend fun getRestaurantsByCuisine(@Path("cuisineName") cuisine: String): Response<RestaurantListResponse>

    @GET("a/meal/{id}")
    suspend fun getMealById(@Path("id") id: String): Response<MealResponse>
}