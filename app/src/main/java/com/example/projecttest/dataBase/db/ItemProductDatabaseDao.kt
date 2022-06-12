package com.example.projecttest.dataBase.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projecttest.dataBase.entities.ItemProduct


@Dao
interface ItemProductDatabaseDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upset(item:ItemProduct)

    @Delete
    suspend fun delete(item:ItemProduct)

    @Query("SELECT * FROM ProductItemDatabase")
    fun getAllItemProductList() : LiveData<List<ItemProduct>>

}