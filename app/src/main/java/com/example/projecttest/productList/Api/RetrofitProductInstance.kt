package com.example.projecttest.productList.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitProductInstance {

    private const val BASE_URL =  "https://fakestoreapi.com"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api:ProductApi by lazy{
        retrofit.create(ProductApi::class.java)
    }

}