package com.example.projecttest.dataBase.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projecttest.dataBase.db.ItemProductDatabase
import com.example.projecttest.dataBase.entities.ItemProduct

class ItemProductRepository(
    private var db: ItemProductDatabase
) {

    suspend fun upsert(item: ItemProduct) = db.getItemProductDatabaseDao().upset(item)

    fun getAllItemProduct() = db.getItemProductDatabaseDao().getAllItemProductList()

}