package com.example.projecttest.dataBase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttest.dataBase.entities.ItemProduct
import com.example.projecttest.dataBase.repository.ItemProductRepository
import com.example.projecttest.productList.model.ProductItem
import kotlinx.coroutines.launch
import retrofit2.Response

class ItemProductViewModel(private val repository: ItemProductRepository) : ViewModel() {


    fun upsert(item: ItemProduct){
        viewModelScope.launch {
            repository.upsert(item)
        }
    }

    fun getAllItemProductList() = repository.getAllItemProduct()

    fun delete(item: ItemProduct){
        viewModelScope.launch {
            repository.delete(item)
        }
    }

}