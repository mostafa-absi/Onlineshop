package com.lib.Onlineshop.data.remote

import com.lib.Onlineshop.data.Model.LoginToken
import com.lib.Onlineshop.data.Model.ProductModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginToken


    @GET("products")
    suspend fun getProducts(): List<ProductModel>
}