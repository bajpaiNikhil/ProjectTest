package com.example.projecttest.dataBase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ProductItemDatabase")
data class ItemProduct(

    @ColumnInfo(name = "ProductTitle")
    val name : String  ,

    @ColumnInfo(name = "ProductAmount")
    val productAmount : Int ,

    @ColumnInfo(name = "ProductImage")
    val productImage : String ,

    @ColumnInfo(name = "SelectedList")
    var selectedList : Boolean ,

    @ColumnInfo(name = "DispatchList")
    var dispatchList : Boolean ,

    @ColumnInfo(name = "PickedList")
    var pickedList : Boolean
){
    @PrimaryKey(autoGenerate = true)
    var productPK : Int? =  null
}


