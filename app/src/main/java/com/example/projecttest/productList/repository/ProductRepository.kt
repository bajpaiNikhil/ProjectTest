package com.example.projecttest.productList.repository

import android.util.Log
import com.example.projecttest.productList.Api.RetrofitProductInstance
import com.example.projecttest.productList.model.ProductItem
import retrofit2.Response

class ProductRepository {

    suspend fun getProductList(): Response<List<ProductItem>>{
        Log.d("ProductRepository" , "from repository")
        return RetrofitProductInstance.api.getProduct()
    }

}