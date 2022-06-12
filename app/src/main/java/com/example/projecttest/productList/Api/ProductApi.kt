package com.example.projecttest.productList.Api

import com.example.projecttest.productList.model.ProductItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("/products")
    suspend fun getProduct(): Response<List<ProductItem>>
}