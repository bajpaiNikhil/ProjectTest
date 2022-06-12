package com.example.projecttest.dataBase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projecttest.dataBase.entities.ItemProduct
import com.example.projecttest.productList.ui.ProductListFragment


@Database(
    entities = [ItemProduct::class] ,
    version = 1
)
abstract class ItemProductDatabase : RoomDatabase() {

    abstract fun getItemProductDatabaseDao() : ItemProductDatabaseDao

    companion object{


        @Volatile
        private var instance  : ItemProductDatabase? =null
        private var lock = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?:createDatabase(context).also{ instance= it}
        }


        private fun createDatabase(context : Context)=
            Room.databaseBuilder(context.applicationContext , ItemProductDatabase::class.java ,
                "ItemProductDatabase.db").build()
    }


}